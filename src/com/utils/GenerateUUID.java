package com.utils;

import java.util.UUID;

public class GenerateUUID {
	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String uuidStr = uuid.toString().replace("-", "").substring(0, 20);
		return uuidStr;
	}
}