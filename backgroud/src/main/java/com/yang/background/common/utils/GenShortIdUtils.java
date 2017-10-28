package com.yang.background.common.utils;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class GenShortIdUtils {
	private static final Logger logger = LoggerFactory.getLogger(GenShortIdUtils.class);
	private static String localHostSuffix;
	private static Date START_DAY = null;
	/**
	 * 100 on base62
	 */
	private static int index = 3844;
	/**
	 * ZZZ on base62
	 */
	private static final int max = 238327;

	static {
		try {
			InetAddress localHost = LocalIpUtils.getLocalAddress();
			@SuppressWarnings("static-access")
			String ipAddress = localHost.getLocalHost().toString();
			logger.info("localIpAddress:" + ipAddress);
			localHostSuffix = ipAddress.substring(ipAddress.lastIndexOf(".") + 1);
		} catch (Exception e) {
			logger.error("未能成功获取ip", e);
		}

		Calendar time = Calendar.getInstance();
		time.set(2013, 5, 1, 0, 0, 0);
		START_DAY = time.getTime();
	}

	public static long getTimeStamp() {
		long nowDate = new Date().getTime();
		return nowDate - START_DAY.getTime();
	}

	public static synchronized int incIndex() {
		index += (RandomUtils.nextInt(4, 4) + 1);
		if (index > max)
			index = 1296;
		return index;
	}

	public static String generateShortId() throws Exception {
		long ts = getTimeStamp();
		int currIndex = incIndex();
		String decimalText = null;
		if (StringUtils.isEmpty(localHostSuffix)) {
			// 此处在分布式环境会产生重复id
			decimalText = StringUtils.reverse(Long.toString(ts) + Integer.toString(currIndex));
		} else {
			decimalText = StringUtils.reverse(Long.toString(ts) + Integer.toString(currIndex) + localHostSuffix);
		}
		return DecimalConverter.fromDecimal(decimalText, 62);
	}

	public static void main(String[] args) throws Exception {

		Set<String> idSet = new HashSet<String>();
		for (int i = 0; i < 10000; i++) {
			String id = generateShortId();
			idSet.add(id);
			System.out.println("id is:" + id);
		}

		if (idSet.size() != 10000) {
			System.out.println("有重复" + idSet.size());
		}

	}
}
