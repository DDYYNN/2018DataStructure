package www.Q10_1.com;

class SimpleNumber {
	int num = 0;
	private SimpleNumber(){}
	public void addNum(int n){ num += n; }
	public void showNum(){ System.out.println(num); }
	
//	private static SimpleNumber sn = null;		// private 선언하여 외부 변경 막기
//	
//	public static SimpleNumber getSimpleNumberInst(){
//		if (sn == null) {						// 딱 한번만 생성된다는 보장 : 처음에 null instance 생성되게끔 한다.
//			sn = new SimpleNumber();
//		}
//		return sn;
//	}
	
	private static SimpleNumber sn = new SimpleNumber();
	
	public static SimpleNumber getSimpleNumberInst(){
		return sn;
	}
}

class OnlyOneInstance{
	public static void main(String[] args){
		SimpleNumber num1 = SimpleNumber.getSimpleNumberInst();
		num1.addNum(20);
		
		System.out.println(num1);
		
		SimpleNumber num2 = SimpleNumber.getSimpleNumberInst();
		num2.addNum(30);
		
		num1.showNum();
		num2.showNum();
		
		System.out.println(num2);
	}
}
