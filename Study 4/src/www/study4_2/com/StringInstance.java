package www.study4_2.com;

public class StringInstance {
	public static void main(String[] args){
		java.lang.String str = "My name is Sunny";
		
		int strlen1 = str.length();
		System.out.println(strlen1);
		
		int strlen2 = "한글".length();			// "..."처럼 큰따옴표를 치는 즉시 String 인스턴트가 생성된다.
		System.out.println(strlen2);
		
		
		
		
		String str1 = "My string";
		String str2 = "My string";
		String str3 = "Your string";
		
		if (str1 == str2) System.out.println("같은 인스턴트 참조");		// String 인스턴트는 같은 문자열이면 같은 참조값을 이용한다.
		else System.out.println("다른 인스턴트 참조");
		
		if (str3 == str2) System.out.println("같은 인스턴트 참조");
		else System.out.println("다른 인스턴트 참조");
		
		
		
		
		
		String str4 = "Smart";
		String str5 = " and ";
		String str6 = "Simple";
		String str7 = str4.concat(str5).concat(str6);
		String str8 = str4 + str5 + str6;				// +로도 문자열 합성 가능.
		
		System.out.println(str7);
		System.out.println("Length = " + str7.length());
		
		System.out.println(str8);
		
		if(str4.compareTo(str6) < 0)					// this가 anotherString보다 앞서면 음수, 뒤면 양수.
			System.out.println("str4가 앞선다");
		else
			System.out.println("str6이 앞선다");
		
		
		
		
		
		String str11 = "Lemon";
		String str22 = "Lemon";
		String str33 = new String(str11);				// 생성과 동시에 복사를 이용하면 강제로 다른 참조값을 가진다.
		
		if (str11 == str22) System.out.println("str11과 str22는 같은 인스턴트 참조");
		else System.out.println("str11과 str22는 다른 인스턴트 참조");
		
		if (str11 == str33) System.out.println("str11과 str33은 같은 인스턴트 참조");
		else System.out.println("str11과 str33은 다른 인스턴트 참조");		// str11과 str33은 내용은 같지만 다른 참조값을 가진다.
	}
}
