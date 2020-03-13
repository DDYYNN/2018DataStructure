package www.Q_13_3.com;

import arraycontrol.*;

public class MaxMin {
	public static int minValue(int[] arr){
		int min;
		min = arr[0];
		for(int e : arr){
			if(e < min) min = e;
		}
		return min;
	}
	
	public static int maxValue(int[] arr){
		int max;
		max = arr[0];
		for(int e : arr){
			if(e > max) max = e;
		}
		return max;
	}
	
	public static void main(String[] args){
		int[] arr = IntArray.getArr1();
		IntArray.showArr1(arr);
		System.out.println("\nmin : " + minValue(arr));
		System.out.println("Max : " + maxValue(arr));
	}
}
