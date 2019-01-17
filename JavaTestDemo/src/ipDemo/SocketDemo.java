package ipDemo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

/**
 * Socket编程 客户端给服务端发送信息，服务端输出信息到控制台
 * 
 * @author riverplant
 *
 */
public class SocketDemo {

	@Test // 客户端
	public void client() {
		Socket socket = null;
		OutputStream os = null;
		// 1.创建Socket
		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
			// 2.创建连接
			os = socket.getOutputStream();
			System.out.println("客户端连接建立，开始准备传输..");
			// 3.向服务端传递信息
			os.write("我是客户端".getBytes());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 4.关闭
			if (os != null) {
				try {
					os.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} // if
			if (socket != null) {
				try {
					socket.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			} // if

		}
	}

	@Test // 服务端
	public void server() throws IOException {
		ServerSocket ss = null;
		Socket s = null;
		InputStream is = null;
		File f = new File("J:\\test.txt");
		BufferedWriter bw = null;
		try {
			ss = new ServerSocket(9090);
			s = ss.accept();
			is = s.getInputStream();
			bw = new BufferedWriter(new FileWriter(f));
			System.out.println("服务端连接建立，开始准备接收..");
			byte[] b = new byte[20];
			int len;
			while ((len = is.read(b)) != -1) {
				String str = new String(b, 0, len);
				bw.write(str);
				System.out.println("收到来自"+s.getInetAddress().getHostName()+"的连接请求");
				System.out.println(str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
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
