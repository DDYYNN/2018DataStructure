package www.study8_5.com;

class AAA{
	public int num = 2;
}

class BBB extends AAA{
	public int num = 5;
	public void showSuperNum(){
		System.out.println("AAA's num: " + super.num);
	}
}

class CCC extends BBB{
	public int num = 7;
	public void showAllNums(){
		showSuperNum();
		System.out.println("BBB's num: " + super.num);
		System.out.println("CCC's num: " + num);
	}
}

public class ShowAllReDecl {
	public static void main(String[] args){
		CCC ref = new CCC();
		ref.showAllNums();
	}
}
