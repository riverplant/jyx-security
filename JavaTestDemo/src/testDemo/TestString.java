package testDemo;
/**
 * 实现字符串的反转
 * @author riverplant
 *
 */
public class TestString {

	//1. 
	public String reverse1(String str){
		StringBuffer sb = new StringBuffer(str);
		sb.reverse();
		return sb.toString();
	}
	
	public String reverse2(String str){
		
		char[] c = str.toCharArray();
		for(int x=0,y = c.length-1;x<y;x++,y--) {
			char temp = c[x];
			c[x] = c[y];
			c[y] = temp;			
		}
		return new String(c);
	}
	
	public static String reverse3(String str){
		StringBuffer sb =new StringBuffer();
		for(int i=str.length()-1;i>=0;i--) {
			sb.append(str.charAt(i));
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String name = "jie";
		System.out.println(reverse3(name));
	}
}
