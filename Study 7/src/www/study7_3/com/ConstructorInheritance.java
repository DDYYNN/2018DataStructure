package www.study7_3.com;

class AAA {
	int num;
	AAA(int n) { num = n; }
}

class BBB extends AAA {
	BBB() { super(0);}
	
	public int getNum(){
		return num;
	}
}

public class ConstructorInheritance {
	public static void main(String[] args){
		BBB bbb = new BBB();
		System.out.println(bbb.getNum());
	}
}
