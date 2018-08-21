package college.interceptor;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ControllerTraceInterceptor implements HandlerInterceptor {

	// public static final logger LOGGER = new LoggerFactory.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long starTime = System.currentTimeMillis();
		request.setAttribute("startTime", starTime);
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd  HH:mm:ss");
			sb.append("-----------").append(sdf.format(new Date())).append("------------\n");
			HandlerMethod h = (HandlerMethod) handler;
			sb.append("Controller: ").append(h.getBean().getClass().getName()).append("\n");
			sb.append("Method: ").append(h.getMethod().getName()).append("\n");
			sb.append("Params: ").append(getParamString(request.getParameterMap())).append("\n");
			sb.append("URL: ").append(request.getRequestURI()).append("\n");
			System.out.println(sb.toString());
		}
		return true;
	}

	private Object getParamString(Map<String, String[]> parameterMap) {
		StringBuilder sb = new StringBuilder();
		for (Entry<String, String[]> e: parameterMap.entrySet()) {
				sb.append(e.getKey()).append("=");
				String[] value = e.getValue();
				if (value != null && value.length == 1) {
					sb.append(value[0]).append("\t");
				} else {
					sb.append(Arrays.toString(value)).append("\t");
				}
		}
		return sb.toString();
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			sb.append("CostTime  : ").append(executeTime).append("ms").append("\n");
			sb.append("-------------------------------------------------------------------------------");
			System.out.println(sb.toString());
		}

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
