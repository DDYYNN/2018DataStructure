package www.Q11_1_2.com;

public class StringInsert {
	public static void main(String[] args){
		String str = "990208-1012752";
		StringBuilder strbuf = new StringBuilder(str);
		strbuf.deleteCharAt(strbuf.indexOf("-"));
		str = strbuf.toString();
		System.out.println(str);
	}
}
