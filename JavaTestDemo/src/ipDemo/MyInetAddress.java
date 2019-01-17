package ipDemo;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress:java.net包
 * @author riverplant
 *1.InetAddress 用来代表IP地址,一个InetAddress的对象就代表一个IP地址
 *2.如何创建InetAddress对象，getByName(String host)
 */
public class MyInetAddress {

	public static void main(String[] args) throws Exception {
		InetAddress inet = InetAddress.getByName("www.google.ca");//域名或者ip
		
		System.out.println(inet.getHostName());
		System.out.println(inet.getHostAddress());
		InetAddress inet1 = InetAddress.getLocalHost();//获得本地的主机
	}
}
