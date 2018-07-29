package college.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具
 * 
 * @author xuxb
 *
 */
public class SerizlizeUtil {

	/**
	 * 对象序列化为byte数组
	 * 
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ObjectOutputStream outputStream = null;
		ByteArrayOutputStream baos = null;

		try {
			baos = new ByteArrayOutputStream();
			outputStream = new ObjectOutputStream(baos);
			outputStream.writeObject(object);
			return baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 字符串序列化为对象
	 * @param bytes
	 * @return
	 */
	public static Object unSerialize(byte[] bytes) {
		InputStream is = null;
		ObjectInputStream ois = null;
		try {
			is = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(is);
			return ois.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
