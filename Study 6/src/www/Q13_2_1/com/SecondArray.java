package www.Q13_2_1.com;

public class SecondArray {
	public static void addOneDArr(int[] arr, int add){
		for(int i = 0; i < arr.length; i++){
			arr[i] += add;
		}
	}
	
	public static void addTwoDArr(int[][] arr, int add){
		for(int i = 0; i < arr.length; i++){
			addOneDArr(arr[i], add);
		}
	}
	
	public static void showArr(int[][] arr){
		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr[i].length; j++){
				System.out.print(arr[i][j] + " ");
			}
			System.out.print('\n');
		}
	}
	
	public static void main(String[] args){
		int[][] arr = {
				{1, 2, 3, 4},
				{2, 3, 4, 5},
				{3, 4, 5, 6}
		};
		showArr(arr);
		
		addTwoDArr(arr, 2);
		System.out.print('\n');
		showArr(arr);
	}
}
