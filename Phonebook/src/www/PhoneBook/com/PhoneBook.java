package www.PhoneBook.com;

import java.util.Scanner;

class PhoneInfo {
	private String name;
	private String phonenumber;
	private String birthday;
	
	public PhoneInfo(String name, String phonenumber, String birthday){
		this.name = name;
		this.phonenumber = phonenumber;
		this.birthday = birthday;
	}
	
	public PhoneInfo(String name, String phonenumber){
		this(name, phonenumber, null);
	}
	
	public void showInfo(){
		System.out.println(">> 정보 출력...");
		System.out.println("Name : " + name);
		System.out.println("Phone Number : " + phonenumber);
		if(birthday != null) System.out.println("Birthday : " + birthday);
		System.out.println('\n');
	}
}

public class PhoneBook {
	static Scanner keyboard = new Scanner(System.in);
	
	public static void showMenu(){
		System.out.println(">> 선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 프로그램 종료");
		System.out.print("선택 : ");
	}
	
	public static void readData(){
		System.out.println("\n>> 정보를 입력하세요...");
		System.out.print("Name : ");
		String name = keyboard.nextLine();
		System.out.print("Phone Number : ");
		String num = keyboard.nextLine();
		System.out.print("Birthday : ");
		String birth = keyboard.nextLine();
		System.out.print('\n');
		
		PhoneInfo person = new PhoneInfo(name, num, birth);
		person.showInfo();
	}
	
	public static void main(String[] args){
		int flag;
		while(true){
			showMenu();
			flag = keyboard.nextInt();
			keyboard.nextLine();
				/* next()와 nextInt()는 개행문자가 버퍼에 남아있게 된다.
				 * 따라서 next() 또는 nextInt() 뒤에 nextLine()을 쓰면
				 * nextLine()는 버퍼의 개행문자를 인식하여 공백만 입력받게 된다.
				 * 
				 * 그러므로, 이런 경우에는 58행처럼 강제로 .nextLine()를 작성하여
				 * 버퍼의 개행문자를 제거해야 한다.
				 * 
				 * 또는, keyboard.skip("\r\n"); 로 개행문자를 스킵하면 된다.
				 */
			
			if(flag == 1)
				readData();
			else if(flag == 2){
				System.out.println("\n>> 프로그램을 종료합니다.");
				break;
			}
			else
				System.out.println(">>1과 2 중에서 선택해 주세요.");
				/* 다음과 같은 코드도 가능하다 : 
				 * 
				 * String flag;
				 * flag = keyboard.nextLine();
				 * 
				 * switch(flag){
				 * case "1" : 
				 * 		// TODO
				 * case "2" :
				 * 		// TODO
				 * }
				 * 
				 * 즉, switch 구문의 매개변수는 String도 가능하다.
				 * 대신 case 라벨도 String의 형태로 만들어져야 한다.
				 */
			
		}
	}
}
