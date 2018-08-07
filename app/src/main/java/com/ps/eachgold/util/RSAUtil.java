/**************************************************************************
 * Copyright©2004-2016 浙江盘石信息技术股份有限公司
 * All rights reserved.
 * 
 * 项目名称：互金网络平台
 * 版权说明：本软件属浙江盘石信息技术股份有限公司所有，在未获浙江盘石信息技术股份有限公司正式授权情况下，
 *          任何企业和个人，不能获取、阅读、安装、传播本软件涉及的任何受知识产权保护的内容。   
 ***************************************************************************/
package com.ps.eachgold.util;


import android.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * @Description
 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
 * @date 2018年1月25日 下午2:06:31
 * @version V1.0.1
 */

public class RSAUtil {
	public static final String ENCRYPTION_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	public static final String PUBLIC_KEY="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRXYFYTGI9uVipfl9P5loLAWLRIQPpSznBc1ACIpCO/ptKYLXjzunWz2TyCj5OV1yjs9pEIcyOnxs6ESplsUOsEakf6wDgox6sU3A51mQmQlm6ALxtfguurZGOJ0Ksg/gL1q97YWTSMsH9R1slDV95nvMKsQAd4Yd/6i+2/ihaxQIDAQAB";
	/**
	 * 加密
	 */
	public static byte[] encrypt(byte[] data, String keyString, boolean isPublic) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(keyString, isPublic);
		KeyFactory keyFactory = RSAUtil.getKeyFactory(keyAndFactoryMap);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		// 对数据加密
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] result = cipher.doFinal(data);
		return result;
	}

	/**
	 * 
	 * @Description 加密
	 * @author <a href="changjunhui8173@adpanshi.com">changjunhui</a>
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String data) throws Exception {
		Map<String, Object> keyAndFactoryMap = RSAUtil.generateKeyAndFactory(PUBLIC_KEY, true);
		KeyFactory keyFactory = RSAUtil.getKeyFactory(keyAndFactoryMap);
		Key key = RSAUtil.getKey(keyAndFactoryMap);

		// 对数据加密
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key);

		byte[] result = cipher.doFinal(data.getBytes());
		return Base64.encodeToString(result,Base64.NO_WRAP);
	}

	/**
	 * 生成钥匙
	 */
	public static Map<String, Object> generateKeyAndFactory(String keyString, boolean isPublic) throws Exception {
		byte[] keyBytes =  Base64.decode(keyString,Base64.DEFAULT);
		KeyFactory keyFactory = KeyFactory.getInstance(ENCRYPTION_ALGORITHM);
		Key key = null;
		if (isPublic) {
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			key = keyFactory.generatePublic(x509KeySpec);
		} else {
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			key = keyFactory.generatePrivate(pkcs8KeySpec);
		}

		Map<String, Object> keyAndFactoryMap = new HashMap<String, Object>(2);
		keyAndFactoryMap.put("key", key);
		keyAndFactoryMap.put("keyFactory", keyFactory);

		return keyAndFactoryMap;
	}
	/**
	 * 从指定对象中获取钥匙
	 */
	public static Key getKey(Map<String, Object> map) {
		if (map.get("key") == null) {
			return null;
		}
		return (Key) map.get("key");
	}

	/**
	 * 从指定对象中获取钥匙工厂
	 */
	public static KeyFactory getKeyFactory(Map<String, Object> map) {
		if (map.get("keyFactory") == null) {
			return null;
		}
		return (KeyFactory) map.get("keyFactory");
	}

}
