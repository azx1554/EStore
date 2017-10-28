package com.yang.backgroud.common.utils;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class LocalIpUtils {

	/**
	 * 下面这种方式在Linux不能获取本机IP：
	 * 
	 * <pre>
	 * InetAddress inet = InetAddress.getLocalHost();
	 * </pre>
	 * 
	 * 本方法改用网卡的绑定地址，可以取到本机的ip地址
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static InetAddress getLocalAddress() throws IOException {
		InetAddress ip = null;
		Enumeration<?> e1 = NetworkInterface.getNetworkInterfaces();
		out: while (e1.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) e1.nextElement();
			Enumeration<?> e2 = ni.getInetAddresses();
			while (e2.hasMoreElements()) {
				InetAddress ia = (InetAddress) e2.nextElement();
				if (ia instanceof Inet6Address)
					continue;
				if (!ia.isMCNodeLocal() && !ia.isMCSiteLocal() && !ia.isAnyLocalAddress() && !ia.isMCGlobal() && !ia.isLoopbackAddress() && ia.isSiteLocalAddress() && !ia.isLinkLocalAddress()
						&& !ia.isMCLinkLocal() && !ia.isMCOrgLocal() && !ia.isMulticastAddress()) {
					ip = ia;
					break out;
				}
			}
		}
		return ip;
	}

}
