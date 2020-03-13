package www.study8_3.com;

class AAA{
	public void rideMethod() { System.out.println("AAA's method"); }
	public void loadMethod() { System.out.println("void method"); }
}

class BBB extends AAA{
	public void rideMethod() { System.out.println("BBB's method"); }
	public void loadMethod(int num) { System.out.println("int Method : " + num); }
}

class CCC extends BBB{
	public void rideMethod() { System.out.println("CCC's method"); }
	public void loadMethod(double num) { System.out.println("double method : " + num); }
	public void loadMethod() { System.out.println("wow");}
	public void loadMethod(String hello){ System.out.println(hello);}
}

public class RideAndLoad {
	public static void main(String[] args){
		AAA ref1 = new CCC();
		BBB ref2 = new CCC();
		CCC ref3 = new CCC();
		AAA ref4 = new BBB();
		
		ref1.rideMethod();
		ref2.rideMethod();
		ref3.rideMethod();
		// 참조변수의 자료형과 상관없이 무조건 마지막으로 오버라이딩된 메소드만 호출된다!!
		
		ref1.loadMethod();
		ref2.loadMethod(2);
		ref3.loadMethod(2.1);
		ref4.loadMethod();
		ref3.loadMethod("hey");
	}
}
