package www.study4_1.com;

public class Person {
	private int perID;
	private int milID;
	private int birthYear;
	private int birthMonth;
	private int birthDay;
	
	public Person(int perID, int milID, int bYear, int bMonth, int bDay){
		this.perID = perID;					// this는 인스턴트 자신을 가리킨다.
		this.milID = milID;
		this.birthYear = bYear;
		this.birthMonth = bMonth;
		this.birthDay = bDay;
	}
	
	public Person(int perID, int bYear, int bMonth, int bDay){
		this(perID, 0, bYear, bMonth, bDay);	// this는 생성자 내의 생성자 호출을 가리킨다.
	}
	
	public Person(int bYear, int bMonth, int bDay){
		this(0, bYear, bMonth, bDay);		// 오버로드 상황에도 this를 사용할 수 있다.
	}
	
	public void showInfo(){
		System.out.println("민번 : " + perID);
		System.out.println("생년월일 : " + birthYear + "/" + birthMonth + "/" + birthDay);
		if (milID != 0){
			System.out.println("군번 : " + milID + '\n');
		}
		else System.out.println("미필입니다.\n");
	}
}

class PersonMain {
	public static void main(String[] args){
		Person man = new Person(951203, 880102, 1995, 12, 3);
		Person woman = new Person(991107, 1999, 11, 7);
		Person it = new Person(2001, 12, 3);
		
		man.showInfo();
		woman.showInfo();
		it.showInfo();
	}
}
