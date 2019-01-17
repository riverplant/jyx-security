package ipDemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * 客户端给服务端发信息，服务端将信息打印到控制台，同时发送信息到客户端
 * 
 * @author riverplant
 *
 */
public class IpDemo2 {
//InputStream是一个阻塞式输入流，当read方法没有停止的时候会阻塞进程，
//所以需要客户端调用sk.shutdownOutput()来彻底停止服务端InputStream.read的输入!!!!!!!!!
	@Test
	public void client() {
		OutputStream os = null;
		InputStream is = null;
		Socket sk = null;
		try {
			sk = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
			os = sk.getOutputStream();
			os.write("我是客户端".getBytes());
			sk.shutdownOutput();
			// 准备接收服务端
			is = sk.getInputStream();
			byte[] by = new byte[20];
			int len;
			while ((len = is.read(by)) != -1) {
				String str = new String(by, 0, len);
				System.out.println(str);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (sk != null) {
				try {
					sk.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if (os != null) {
				try {
					os.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	@Test
	public void server() {
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			ss = new ServerSocket(9090);
			s = ss.accept();
			is = s.getInputStream();
			byte[] b = new byte[20];
			int length;
			while ((length = is.read(b)) != -1) {
				String str = new String(b, 0, length);
				System.out.println(str);
			}
			os = s.getOutputStream();
			os.write("收到服务端传来的信息".getBytes());

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (s != null) {
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
