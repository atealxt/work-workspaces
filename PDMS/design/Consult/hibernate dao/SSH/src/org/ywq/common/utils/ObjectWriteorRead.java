package org.ywq.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import org.ywq.common.QueryBuilder;

/**
 * @author ai5qiangshao E-mail:ai5qiangshao@163.com
 * @version 创建时间：Aug 9, 2009 5:29:15 PM
 * @Package org.ywq.common.utils
 * @Description 类说明
 */
public class ObjectWriteorRead {
	public static void writeObject(Object o) {
		ObjectWriteorRead.writeObject(o, null);
	}

	public static void writeObject(Object o, OutputStream output) {
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		try {
			ObjectOutputStream out = new ObjectOutputStream(
					output == null ? byteOut : output);
			out.writeObject(o);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object readObject(InputStream input) {
		Object o = null;
		try {
			ObjectInputStream in = new ObjectInputStream(input);
			o = in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return o;
	}

}
