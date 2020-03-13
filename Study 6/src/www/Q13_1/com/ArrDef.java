package www.Q13_1.com;

import java.util.Scanner;

public class ArrDef {
	public static int minValue(int[] arr){
		int min;
		min = arr[0];
		for(int i = 1; i < arr.length; i++){
			if(arr[i] < min)
				min = arr[i];
		}
		return min;
	}
	
	public static int maxValue(int[] arr){
		int max;
		max = arr[0];
		for(int i = 1; i < arr.length; i++){
			if(arr[i] > max)
				max = arr[i];
		}
		return max;
	}
	
	public static void showArr(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int length;
		
		System.out.print("Length of Array : ");
		length = sc.nextInt();
		
		int[] arr = new int[length];				// 동적 할당이 필요없다.
		// 사실, 자바는 동적할당을 할 수 없다. 그래서 List를 많이 사용한다.
		
		for(int i = 0; i < length; i++){
			System.out.printf("%d번째 원소 : ", i+1);
			arr[i] = sc.nextInt();
		}
		showArr(arr);
		
		System.out.println("min Value : " + minValue(arr));
		System.out.println("Max Value : " + maxValue(arr));
		
		sc.close();
	}
}
