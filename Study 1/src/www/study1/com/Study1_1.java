package www.study1.com;

public class Study1_1 {

//	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("Hello");
//		System.out.println("result" + "2+5" + 2+5);		// +는 병렬연결로 해석된다.
//		System.out.println("2+5=" +(2+5));				// +는 연산으로 해석된다.
		
//		int num1 = 1, num2 = 2;
//		int num3 = num1 + num2;
//		boolean tf = (num3 > 3);
//		System.out.println("result = " + tf);
		
//		char ch1 = 0xCB01;				// 0x:16진법,0:8진
//		System.out.println(ch1);
		
//		long num = 10000000000L;		// long형은 L(l)로, float형은 F(f)로 형을 명시해야 한다.
		
//		long num1 = 2147483649L;
//		int num2 = (int)num1;
//		System.out.println(num1 + "," + num2);		// long에서 int로의 명시적 형변환은 상위바이트가 잘려나간다.
		
//		int n = 2;
//		n *= 2.6;									// 2번의 형변환 : n[int] = n[double] * 2.6
//		System.out.println(n);						// n = 5.2에서 0.2는 손실된다.
		
//		int n1 = 0, n2 = 0;							// SCE(Short-Circuit Evaluation)
//		boolean result = ((n1+=10) < 0 && (n2+=10) > 0);
//		System.out.println("result = " + result);
//		System.out.println("n1 = " + n1 + ", n2 = " + n2);	// left side is obviously true -> evaluated with n2+=10 not being operated.
//		단항연산자 +는 int형으로 자동변환이 일어나는 연산이다. 따라서 short n = +2 와 같은 연산은 불가능하다.
		
//		int n1 = 5;
//		int n2 = (n1--) + 6;						// postfix는 ()안에 있어도 맨 마지막에 연산된다.
//		System.out.println(n1 + "," + n2);			// 결과는 4, 11이 나온다.
		
/*		비트 단위 연산자
 		1. 피연산자는 항상 정수여야 한다.
 		2. 종류는 4개다 : &, |, ^(XOR : 값이 다를 때만 1을 반환한다), ~(0과 1을 반전한다)
 */
/*		비트 쉬프트 연산자
		1. 종류는 3개다 : <<(*(2^n)), >>(/(2^n) : 음수의 경우 빈공간은 1로 채운다), >>>(음수도 빈공간은 0으로 채운다)
 */
//		int n = 15678;								// 오른쪽에서 3번째, 5번째 비트를 확인하는 코드
//		System.out.println((n >> 2) & 1);
//		System.out.println((n >> 4) & 1);
		
//		int n = 3;
//		switch (n){
//		case 1 : case 2 : case 3 : 					// case label은 한번에 여러개 붙일 수도 있다.
//			System.out.println("Hello");
//			break;
//		case 4 : case 5 :
//			System.out.println("Bye");
//			break;}
		
// 문제 5-3-2
//		int n = 24;
//		switch (n / 10){
//		case 0 : 
//			System.out.println("0 이상 10 미만");
//			break;
//		case 1 :
//			System.out.println("10이상 20 미만");
//			break;
//		case 2 : 
//			System.out.println("20이상 30 미만");
//			break;
//		default : 
//			System.out.println("음수 혹은 30 이상");
//		}
		
//		for(int i = 0, j = 7; i<j; i++, j--)			// 초기조건, step에는 ,를 이용해서 구성 가능
//			System.out.println("Java " + i + j);		// for 내부에서 선언된 int는 for 안에서만 효력을 가지므로 for문 밖에서 새로운 int i를 정의할 수 있다.
		
// 문제 5-5-2
//		for(int i = 1; i <= 9; i++)
//			System.out.println("5 * " + i +" = " + (5*i));
		
// 문제 5-6-2
//		int num = 1, sum = 0;
//		while(true){
//			if((num % 2) == 1 || (num % 3) == 0)
//				sum += num;
//			if(sum > 1000)
//				break;
//			num++;
//		}
//		System.out.println("num = "+num);
//		System.out.println("sum = "+sum);

// 문제 5-7-1
//		for(int i = 2; i < 9; i+=2){
//			for(int j = 1; j <=i; j++)
//				System.out.println(i+"*"+j+" = "+ (i*j));
//		}
		
// 문제 5-7-2
//		for(int i = 0; i < 10; i++){
//			for(int j = 0; j < 10; j++){
//				if (11*(i+j) == 99) System.out.println("AB = "+i+j);
//			}
//		}
		
//		outerloop : for(int i = 1; i < 10; i++){			// break로 중첩반복문 탈출 :label을 이용한다(break outerloop)
//			for(int j = 1; j < 10; j++){
//				System.out.println("["+i+", "+j+"]");
//				if(i%2 == 0 && j%2 == 0) break outerloop;
//			}
//		}
		
//// 문제 6-3-2
//
	public static void binary(int n){
		if(n < 2){
			System.out.println(n);
		}
		else{
			System.out.println(n & 1);
			binary(n >> 1);
		}
	}	
		
	public static void main(String[] args){
		binary(104);
	}		
}