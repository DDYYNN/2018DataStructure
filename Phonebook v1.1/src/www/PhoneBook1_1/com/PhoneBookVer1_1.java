package www.PhoneBook1_1.com;

import java.util.Scanner;

class PhoneInfo {
	private String name;
	private String phonenumber;
	private String birthday;

	public PhoneInfo(String name, String phonenumber, String birthday) {
		this.name = name;
		this.phonenumber = phonenumber;
		this.birthday = birthday;
	}

	public PhoneInfo(String name, String phonenumber) {
		this(name, phonenumber, null);
	}

	public void showInfo() {
		System.out.println(">> 정보 출력...");
		System.out.println("Name : " + name);
		System.out.println("Phone Number : " + phonenumber);
		if (birthday != null)
			System.out.println("Birthday : " + birthday);
	}

	public String getName() {
		return name;
	}
}

class PhoneBookManager {
	final int MAX_COUNT = 100;
	PhoneInfo[] Data = new PhoneInfo[MAX_COUNT];
	int dataCount = 0;

	public void inputData() {
		if (dataCount >= MAX_COUNT)
			System.out.println(">> 데이터 용량 초과!");
		else {
			System.out.println("\n>> 정보를 입력하세요...");
			System.out.print("Name : ");
			String name = Menu.sc.nextLine();
			System.out.print("Phone Number : ");
			String num = Menu.sc.nextLine();
			System.out.print("Birthday : ");
			String birth = Menu.sc.nextLine();
			System.out.print('\n');
			Data[dataCount] = new PhoneInfo(name, num, birth);
			dataCount++;
			System.out.println(">> 데이터 입력 완료!\n");
		}
	}

	private int dataIndex(String keyname) {
		int result = -1;

		for (int i = 0; i < dataCount; i++) { // null 인스턴스랑 비교되는 순간 에러난다. 조심!
			if (keyname.compareTo(Data[i].getName()) == 0) {
				result = i;
			} // name, keyname은 String 내용이 아니라 참조값이다!!!
			// 그러니까 compareTo를 써야지 !!
		}
		return result;
	}

	public void serachData() {
		System.out.print(">> 이름을 검색하세요 : ");
		int index = dataIndex(Menu.sc.nextLine());
		if (index < 0)
			System.out.println(">> 데이터 검색 결과가 없습니다.");
		else
			Data[index].showInfo();
	}

	public void deleteData() {
		System.out.print(">> 삭제할 이름을 입력하세요 : ");
		int index = dataIndex(Menu.sc.nextLine());
		if (index < 0)
			System.out.println(">> 데이터 검색 결과가 없습니다.");
		else {
			for (int i = index; i < dataCount - 1; i++) { // 조건 정확하게 찾자!
				Data[i] = Data[i + 1]; // 인스턴스 자체의 대체는 가능하다.
			}
			// Data[dataCount-1] = null; // Data[dataCount] = null이므로 마지막값은 자동으로
			// null처리되어 사라진다.
			dataCount--;
			System.out.println(">> 삭제 완료!");
		}
	}
}

class Menu {
	public static Scanner sc = new Scanner(System.in); // 여러 클래스에서 Scanner를 쓰면
														// static 선언해서 한번에 쓰게
														// 한다.

	public static void chooseMenu() {
		System.out.println(">> 선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		System.out.print("선택 : ");
	}
}

public class PhoneBookVer1_1 {
	public static void main(String[] args) {
		PhoneBookManager storage = new PhoneBookManager(); // 배열 내 모든 인스턴스가
															// null로 초기화된다.
		// PhoneBookManager 인스턴스를 생성해야 PhoneBookManager 메소드를 자유롭게 이용할 수 있다.
		while (true) {
			System.out.println("\n[현재 " + storage.dataCount + "명 저장]");
			Menu.chooseMenu();
			switch (Menu.sc.nextLine()) {
			case "1":
				storage.inputData();
				break;
			case "2":
				storage.serachData();
				break;
			case "3":
				storage.deleteData();
				break;
			case "4":
				System.out.println(">> 프로그램을 종료합니다.");
				return;
			default:
				System.out.println(">> 잘못된 입력값입니다. ");
			}
		}
	}
}
