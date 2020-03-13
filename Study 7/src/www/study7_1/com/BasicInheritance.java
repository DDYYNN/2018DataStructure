package www.study7_1.com;

class Man {
	private String name;
	public Man(String name){ this.name = name; }
	public void tellYourName(){
		System.out.println("이름 : " + name);
	}
}

class BusinessMan extends Man {						// super 생성자가 없으면 인스턴스 생성이 안 되나?
	private String company;
	private String position;
	
	public BusinessMan(String name, String company, String position){
		super(name);								// 없으면 컴파일이 안됨.
		this.company = company;
		this.position = position;
	}
	
	public void tellYourInfo(){
		tellYourName();
		System.out.println("회사 : " + company);
		System.out.println("직급 : " + position);
	}
}

public class BasicInheritance {
	public static void main(String[] args){
		BusinessMan man1 = new BusinessMan("Mr. Hong", "Hybrid 3d ELD", "Staff Eng.");
		man1.tellYourInfo();
		man1.tellYourName();
	}
}
