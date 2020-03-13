package www.study8_6.com;

import java.util.Scanner;

class Friend{
	String name, phoneNum, addr;
	public Friend(String name, String num, String addr){
		this.name = name;
		this.phoneNum = num;
		this.addr = addr;
	}
	public void showData(){
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phoneNum);
		System.out.println("주소 : " + addr);
	}
	public void showBasicData(){};
}

class HighFriend extends Friend{
	String work;
	public HighFriend(String name, String num, String addr, String work){
		super(name, num, addr);
		this.work = work;
	}
	public void showData(){
		super.showData();
		System.out.println("직업 : " + work);
	}
	public void showBasicData(){
		System.out.println("이름 : " + name);
		System.out.println("전화번호 : " + phoneNum);
	}
}

class UnivFriend extends Friend{
	String major;
	public UnivFriend(String name, String num, String addr, String major){
		super(name, num, addr);
		this.major = major;
	}
	public void showData(){
		super.showData();
		System.out.println("전공 : " + major);
	}
	public void showBasicData(){
		System.out.println("이름 : " + name);
		System.out.println("전공 : " + major);
	}
}

class FriendInfoHandler{
	private Friend[] myFriends;
	private int numOfFriends = 0;
	
	public FriendInfoHandler(int n){
		myFriends = new Friend[n];
		numOfFriends = 0;
	}
	
	private void addFriendInfo(Friend fren){				// 오버라이딩 됐으니 형을 Friend로 뭉뚱그리자!
		myFriends[numOfFriends++] = fren;
	}
	
	public void addFriend(int choice){
		String name, phoneNum, addr, work, major;
		System.out.print("이름 : "); name = S.sc.nextLine();
		System.out.print("전화번호 : "); phoneNum = S.sc.nextLine();
		System.out.print("주소 : "); addr = S.sc.nextLine();
		
		if (choice == 1){
			System.out.print("직업 : "); work = S.sc.nextLine();
			addFriendInfo(new HighFriend(name, phoneNum, addr, work));
		}
		else{
			System.out.print("전공 : "); major = S.sc.nextLine();
			addFriendInfo(new UnivFriend(name, phoneNum, addr, major));
		}
		System.out.println("입력완료!");
	}
	
	public void showAllData(){
		for(int i = 0; i < numOfFriends; i++){
			myFriends[i].showData();
			System.out.println("");
		}
	}
	
	public void showSimpleData(){
		for(int i = 0; i < numOfFriends; i++){
			myFriends[i].showBasicData();
			System.out.println("");
		}
	}
}

class S{
	public static void showMenu(){
		System.out.println("*** 메뉴 선택***");
		System.out.println("1. 고교 정보 저장");
		System.out.println("2. 대학 친구 저장");
		System.out.println("3. 전체 정보 출력");
		System.out.println("4. 기본 정보 출력");
		System.out.println("5. 프로그램 종료");
		System.out.print("선택 : ");
	}
	public static Scanner sc = new Scanner(System.in);
}

public class MyFriendInfoBook {
	public static void main(String[] args){
		FriendInfoHandler handler = new FriendInfoHandler(10);
		while(true){
			S.showMenu();
			int choice = S.sc.nextInt();
			S.sc.nextLine();
			
			switch(choice){
			case 1: case 2:
				handler.addFriend(choice);
				break;
			case 3:
				handler.showAllData();
				break;
			case 4:
				handler.showSimpleData();
				break;
			case 5:
				System.out.println("Bye!");
				return;
			}
		}
	}
}
