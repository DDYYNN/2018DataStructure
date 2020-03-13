package www.study2.com;

class Number {
	int num = 0;
	public void addNum(int n){
		num += n;
	}
	public int getNumber(){
		return num;
	}
}

class PassInstance {
	public static void main(String[] args){
		Number nInst = new Number();
		System.out.println("Before method call : " + nInst.getNumber());
		
		simpleMethod(nInst);
		System.out.println("After method call : " + nInst.getNumber());
	}
	
	public static void simpleMethod(Number numb){
		numb.addNum(12); 				// numb는 nInst와 같은 객체를 참조한다.
		// 따라서 simpleMethod가 실행되면 nInst.num의 값이 직접 바뀌게 된다.
	}
}