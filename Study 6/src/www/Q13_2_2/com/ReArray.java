package www.Q13_2_2.com;

import arraycontrol.*;

public class ReArray {
	public static void reArray(int arr[][]){
		int n = arr.length;
		int[] tmp = arr[n-1];
		
		for(int i = n-1; i > 0; i--){
			arr[i] = arr[i-1];
		}
		arr[0] = tmp;
	}
	
	public static void main(String[] args){
		int[][] arr = IntArray.randArr2();
		IntArray.showArr2(arr);
		
		reArray(arr);
		IntArray.showArr2(arr);
	}
}
