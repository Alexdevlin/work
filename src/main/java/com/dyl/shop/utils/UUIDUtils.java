package com.dyl.shop.utils;

import java.util.UUID;

/**
 * 生成UUID工具类
 * @author duyunlei
 * 2015-9-4 下午9:25:37
 *
 */
public class UUIDUtils {
	
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-", "");
	}

}
