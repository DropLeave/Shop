package utils;

import java.util.UUID;

/**
 * ���ɼ�����
 * @author Administrator
 *
 */
public class UUIDutils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
