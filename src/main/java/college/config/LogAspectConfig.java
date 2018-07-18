package college.config;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import college.log.MethodLog;

@Aspect
@Component
public class LogAspectConfig {

	private final static Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);

	private static PropertiesConfiguration config = null;
	
	public LogAspectConfig() {
		Configurations configs = new Configurations();
        // setDefaultEncoding是个静态方法,用于设置指定类型(class)所有对象的编码方式。
        // 本例中是PropertiesConfiguration,要在PropertiesConfiguration实例创建之前调用。
        FileBasedConfigurationBuilder.setDefaultEncoding(PropertiesConfiguration.class, "UTF-8");
        try {
			config = configs.properties(this.getClass().getClassLoader().getResource("methodlog.properties"));
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Pointcut("@annotation(college.log.MethodLog)")
	public void logAspect() {

	}

	@Before("logAspect()")
	public void doBefore(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		// 请求的IP
		String ip = getIpAddr(request);
		try {
			// System.out.println("请求方法:" +
			// (joinPoint.getTarget().getClass().getName() + "." +
			// joinPoint.getSignature().getName() + "()"));
			// System.out.println("方法描述:" +
			// getControllerMethodDescription(joinPoint));
			// System.out.println("请求人:" + loginUser.getUserId());
			// System.out.println("请求IP:" + ip);
			String url = request.getRequestURL().toString();
			String param = request.getQueryString();
			String type = request.getMethod();
			// PermissionsPo permissionsPo =
			// getControllerMethodPermissions(joinPoint);]
			String[] Logs = getControllerMethodDescription(joinPoint);
		} catch (Exception e) {
			// 记录本地异常日志
			logger.error("异常信息:{}", e.getMessage());
		}
	}

	private String[] getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String[] logs = new String[2];
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					MethodLog log = method.getAnnotation(MethodLog.class);
					String key = log.key();
					// logs[0] = log.description();
					// logs[1] = log.path();
					 logs[0] = config.getString(key);
//					 logs[1] = config.getString(key);
					break;
				}
			}
		}
		return logs;
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			// 这里主要是获取本机的ip,可有可无
			if (ip.equals("127.0.0.1") || ip.endsWith("0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ip = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		return ip != null && !"".equals(ip) ? ip.split(",")[0] : null;
	}
}
