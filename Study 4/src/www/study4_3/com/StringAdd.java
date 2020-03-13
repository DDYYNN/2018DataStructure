package www.study4_3.com;

public class StringAdd {
	public static void main(String[] args){
		String str1 = "Lemon" + "ade";
		String str2 = "Lemon" + 'A';
		String str3 = "Lemon" + 3;					// 실제로는 16행과 같이 컴파일된다.
		String str4 = 1 + "Lemon" + 2;
		str4 += '!';
		
		System.out.println(str1);
		System.out.println(str2);
		System.out.println(str3);
		System.out.println(str4);
		
		String str5 = "Lemon".concat(String.valueOf(3));
		System.out.println(str5);
		
		
	}
}
