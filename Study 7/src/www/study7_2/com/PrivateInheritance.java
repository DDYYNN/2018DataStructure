package www.study7_2.com;

class Accumulator {
	private int val;
	
	Accumulator(int init) { val = init; }
	protected void accumulate(int num){
		if(num < 0)
			return;
		val+=num;
	}
	protected int getAccVal(){ return val; }
}

class SavingAccount extends Accumulator {
	public SavingAccount(int initDep){
		super(initDep);
	}
	public void saveMoney(int Money){
		accumulate(Money);
	}
	public void showSavedMoney(){
		System.out.print("지금까지의 누적 금액 : ");
		System.out.println(getAccVal());
	}
}

public class PrivateInheritance {
	public static void main(String[] args){
		SavingAccount sa = new SavingAccount(1000);
		sa.saveMoney(500);
		sa.saveMoney(1000);
		sa.showSavedMoney();
	}
}
