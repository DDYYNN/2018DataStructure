package www.PhoneBookVer04.com;

import java.util.Scanner;

class PhoneInfo {
	private String name;
	private String phonenumber;

	public PhoneInfo(String name, String phonenumber) {
		this.name = name;
		this.phonenumber = phonenumber;
	}

	public void showInfo() {
		System.out.println(">> 정보 출력...");
		System.out.println("Name : " + name);
		System.out.println("Phone Number : " + phonenumber);
	}

	public String getName() {
		return name;
	}
}

class PhoneUnivInfo extends PhoneInfo {
	private String major;
	private int year;

	public PhoneUnivInfo(String name, String phonenumber, String major, int year) {
		super(name, phonenumber);
		this.major = major;
		this.year = year;
	}

	public void showInfo() {
		super.showInfo();
		System.out.println("Major : " + major);
		System.out.println("Year : " + year);
	}
}

class PhoneCompanyInfo extends PhoneInfo {
	private String company;

	public PhoneCompanyInfo(String name, String phonenumber, String company) {
		super(name, phonenumber);
		this.company = company;
	}

	public void showInfo() {
		super.showInfo();
		System.out.println("Company : " + company);
	}
}

class PhoneBookManager {
	final int MAX_COUNT = 100;
	private PhoneInfo[] Data = new PhoneInfo[MAX_COUNT];
	private int dataCount = 0;

	private PhoneInfo readFriendInfo() {
		System.out.print("Name : ");
		String name = Menu.sc.nextLine();
		System.out.print("Phone Number : ");
		String num = Menu.sc.nextLine();
		return new PhoneInfo(name, num);
	}

	private PhoneInfo readUnivFriendInfo() {
		System.out.print("Name : ");
		String name = Menu.sc.nextLine();
		System.out.print("Phone Number : ");
		String num = Menu.sc.nextLine();
		System.out.print("Major : ");
		String major = Menu.sc.nextLine();
		System.out.print("Year : ");
		int year = Menu.sc.nextInt();
		Menu.sc.nextLine();
		return new PhoneUnivInfo(name, num, major, year);
	}

	private PhoneInfo readCompanyFriendInfo() {
		System.out.print("Name : ");
		String name = Menu.sc.nextLine();
		System.out.print("Phone Number : ");
		String num = Menu.sc.nextLine();
		System.out.print("Company : ");
		String company = Menu.sc.nextLine();
		return new PhoneCompanyInfo(name, num, company);
	}

	public void inputData() {
		if (dataCount >= MAX_COUNT) {
			System.out.println(">> 데이터 용량 초과!");
			return;
		}
		System.out.println("\n>> 정보를 입력하세요...");
		System.out.println("1. 일반 2. 대학 3. 회사");
		System.out.print(">> 선택 : ");
		int choice = Menu.sc.nextInt();
		Menu.sc.nextLine();
		PhoneInfo tmp = null;

		switch (choice) {
		case 1:
			tmp = readFriendInfo();
			break;
		case 2:
			tmp = readUnivFriendInfo();
			break;
		case 3:
			tmp = readCompanyFriendInfo();
			break;
		}
		Data[dataCount++] = tmp;
		System.out.println(">> 데이터 입력 완료!\n");
	}

	private int dataIndex(String keyname) {
		int result = -1;

		for (int i = 0; i < dataCount; i++) {
			if (keyname.compareTo(Data[i].getName()) == 0) {
				result = i;
			}
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
			for (int i = index; i < dataCount - 1; i++) {
				Data[i] = Data[i + 1];
			}
			dataCount--;
			System.out.println(">> 삭제 완료!");
		}
	}

	public int getCount() {
		return dataCount;
	}
}

class Menu {
	public static Scanner sc = new Scanner(System.in);

	public static void chooseMenu() {
		System.out.println(">> 선택하세요...");
		System.out.println("1. 데이터 입력");
		System.out.println("2. 데이터 검색");
		System.out.println("3. 데이터 삭제");
		System.out.println("4. 프로그램 종료");
		System.out.print("선택 : ");
	}
}

public class PhoneBookVer04 {
	public static void main(String[] args) {
		PhoneBookManager storage = new PhoneBookManager();
		while (true) {
			System.out.println("\n[현재 " + storage.getCount() + "명 저장]");
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
