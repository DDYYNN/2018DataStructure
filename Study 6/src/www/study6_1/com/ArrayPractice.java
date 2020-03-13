package www.study6_1.com;

public class ArrayPractice {
	
	public static int[] addAllArray(int[] ar, int addVal){
		for(int i = 0; i < ar.length; i++){				// length 인스턴스 변수가 존재한다.
			ar[i] += addVal;
		}
		return ar;										// ar의 참조값이 반환된다.
	}
	
	public static void main(String[] args){
		String[] strArr = new String[3];
		strArr[0] = new String("Java");
		strArr[1] = "Flex";
		strArr[2] = "Ruby";
		
		for (int i = 0; i < strArr.length; i++){
			System.out.println(strArr[i]);
		}
		
		int[] arr = {1, 2, 3, 4, 5};
		int[] ref;
		
		ref = addAllArray(arr, 7);						// ar의 참조값이 반환된다.
		
		if(arr == ref)
			System.out.println("동일 배열 참조");
		else
			System.out.println("다른 배열 참조");
		
		for(int i = 0; i < ref.length; i++){
			System.out.print(arr[i]+" ");
		}
	}
}
