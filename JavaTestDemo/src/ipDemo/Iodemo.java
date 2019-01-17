package ipDemo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

/**
 * IO编程,字节流,字符流
 * 
 * @author riverplant
 *
 */
public class Iodemo {
	// private String FilePath="J:\\test.txt";
	public FileInputStream fis = null;
	public FileOutputStream fos = null;
	public BufferedReader br = null;
	public OutputStream os = null;
	public BufferedWriter bw = null;

	/**
	 * 读取文件
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void createFile(String path, String fileName) throws IOException {
		File file_path = new File(path);
		if (!file_path.isDirectory())
			file_path.mkdirs();
		// File[] files = file_path.listFiles();

		File file = new File(path + fileName);
		if (!file.exists())
			file.createNewFile();

	}

	public static void main(String[] args) throws IOException {
		Iodemo demo = new Iodemo();
		// demo.ReadFile("J:\\test\\", "demo1.txt", "demo2.txt");
		demo.bf();
	}

	/**
	 * 读取文件
	 * 
	 * @param path
	 * @throws IOException
	 */
	public void ReadFile(String path, String fileName, String fileName2) {

		try {
			createFile(path, fileName);
			createFile(path, fileName2);
			File file = new File(path + fileName);
			File file2 = new File(path + fileName2);
			// StringBuffer sb = new StringBuffer();
			fis = new FileInputStream(file);
			fos = new FileOutputStream(file2);
			// 定义一个字节数组,相当于缓存
			// \r\n回车换行
			byte[] bytes = new byte[1024];
			int n = 0;// 实际读取的字节数
			while ((n = fis.read(bytes)) != -1) {
				String s = new String(bytes, 0, n);
				// fos.write(s.getBytes());
				fos.write(bytes, 0, n);
				// sb.append(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
				if (fis != null)
					fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
	}

	/**
	 * 图片流操作
	 */
	/**
	 * 
	 * @param path1
	 * @param path2
	 * @param fileName
	 * @param file2Name
	 */
	public void imageIo(String path1, String path2, String fileName, String file2Name) {
		// 把图片读入内存
		// 二进制文件只能用字节流
		File f = new File("J:\\jie.jpg");
		File f2 = new File("J:\\jie2.jpg");
		FileInputStream fis = null;
		FileOutputStream fss = null;
		if (f.exists()) {
			try {
				fis = new FileInputStream(f);
				fss = new FileOutputStream(f2);
				byte[] byt = new byte[1024];
				int len = 0;
				while ((len = fis.read(byt)) != -1) {
					fss.write(byt, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (fss != null)
						fss.close();
					if (fis != null)
						fis.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}

	}

	public void fileReadTest() {
		// 字符流对象
		FileReader fr = null;
		FileWriter fw = null;
		try {
			fr = new FileReader("J:\\test\\demo1.txt");
			fw = new FileWriter("J:\\test\\demo3.txt");
			// 创建缓存
			char c[] = new char[1024];
			int len = 0;
			while ((len = fr.read(c)) != -1) {
				String s = new String(c, 0, len);
				System.out.println(s);
				fw.write(c, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 不关闭流会导致写入失败
			try {
				if (fw != null)
					fw.close();
				if (fr != null)
					fr.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/**
	 * 缓冲字符流
	 */
	public void bf() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new FileReader("J:\\test\\demo1.txt"));
			bw = new BufferedWriter(new FileWriter("J:\\test\\demo5.txt"));
			String ss = "";
			while ((ss = br.readLine()) != null) {
				System.out.println(ss);
				bw.write(ss+"\r\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (br != null)
					br.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

}
