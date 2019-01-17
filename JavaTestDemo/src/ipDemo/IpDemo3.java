package ipDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
 * 客户端发送文件给服务端，服务端保存到本地，并返回"发送成功"给客户端，并关闭相应的连接
 * 
 * @author riverplant
 *
 */
public class IpDemo3 {
	@Test
	public void client() {
		OutputStream os = null;
		InputStream is = null;
		Socket sk = null;
		BufferedReader br = null;
		try {
			sk = new Socket(InetAddress.getByName("127.0.0.1"), 9090);
			os = sk.getOutputStream();
			File f = new File("J:\\test.txt");
			br = new BufferedReader(new FileReader(f));
			
			StringBuffer sb = new StringBuffer();
			String s;
			while((s=br.readLine())!=null) {
				sb.append(s+"\n");
				System.out.println(sb.toString());	
			}
			os.write(sb.toString().getBytes());
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
			if (br != null) {
				try {
					br.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
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
		BufferedWriter bw = null;
		try {
			ss = new ServerSocket(9090);
			s = ss.accept();
			is = s.getInputStream();
			byte[] b = new byte[20];
			int length;
			bw = new BufferedWriter(new FileWriter("J:\\serverTest.txt"));
			StringBuffer sb = new StringBuffer();
			while ((length = is.read(b)) != -1) {
				String str = new String(b, 0, length);
				sb.append(str);
			}
			bw.write(sb.toString());
			os = s.getOutputStream();
			os.write("发送成功".getBytes());

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
