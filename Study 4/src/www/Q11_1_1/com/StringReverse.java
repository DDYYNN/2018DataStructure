package www.Q11_1_1.com;

public class StringReverse {
	public static void main(String[] args){
		String str = new String("ABCDEFGHIJKLMN");
		StringBuilder strbld = new StringBuilder(str);
		strbld.reverse();
		System.out.println(strbld);
	}
}
