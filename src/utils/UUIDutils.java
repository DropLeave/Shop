package utils;

import java.util.UUID;

/**
 * Éú³É¼¤»îÂë
 * @author Administrator
 *
 */
public class UUIDutils {
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}
