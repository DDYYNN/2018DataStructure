package arraycontrol;

import java.util.Scanner;

public class IntArray {
	public static void showArr1(int[] arr){
		for(int i = 0; i < arr.length; i++){
			System.out.print(arr[i] + " ");
		}
	}
	
	public static void showArr2(int[][] arr){
		for(int i = 0; i < arr.length; i++){
			showArr1(arr[i]);
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public static int[] getArr1(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Columns : ");
		int arr[] = new int[sc.nextInt()];
		for(int i = 0; i < arr.length; i++){
			System.out.printf(">> %dth element : ", i+1);
			arr[i] = sc.nextInt();
		}
		sc.close();
		return arr;
	}
	
	public static int[][] getArr2(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Ragged Array? ");
		if(sc.nextBoolean() == true){
			System.out.print("Rows : ");
			int[][] arr = new int[sc.nextInt()][];
			for(int i = 0; i < arr.length; i++){
				System.out.printf("%dth Columns : ", i+1);
				int[] tmp = new int[sc.nextInt()];
				for(int j = 0; j < tmp.length; j++){
					System.out.printf(">> %dth element : ", j+1);
					tmp[j] = sc.nextInt();
				}
				arr[i] = tmp;
			}
			sc.close();
			return arr;
		}
		else{
			System.out.print("Rows : ");
			int row = sc.nextInt();
			System.out.print("Colums : ");
			int column = sc.nextInt();
			
			int[][] arr = new int[row][column];
			for(int i = 0; i < row; i++){
				System.out.println((i+1) + "th Column");
				for(int j = 0; j < column; j++){
					System.out.printf(">> %dth element : ", j+1);
					arr[i][j] = sc.nextInt();
				}
			}
			sc.close();
			return arr;
		}
	}
	
	
	public static int[] randArr1(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Columns : ");
		int[] arr = makeRandArr(sc.nextInt());
		sc.close();
		return arr;
	}
	
	private static int[] makeRandArr(int n){
		int[] arr = new int[n];
		for(int i = 0; i < arr.length; i++){
			arr[i] = (int)(Math.random() * 1000) % 10;
		}
		return arr;
	}
	
	public static int[][] randArr2(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Ragged Array? ");
		if(sc.nextBoolean() == true){
			System.out.print("Rows : ");
			int[][] arr = new int[sc.nextInt()][];
			for(int i = 0; i < arr.length; i++){
				System.out.printf("%dth Columns : ", i+1);
				arr[i] = makeRandArr(sc.nextInt());
			}
			sc.close();
			return arr;
		}
		else{
			System.out.print("Rows : ");
			int row = sc.nextInt();
			System.out.print("Colums : ");
			int column = sc.nextInt();
			
			int[][] arr = new int[row][column];
			for(int i = 0; i < row; i++){
				for(int j = 0; j < column; j++){
					arr[i][j] = (int)(Math.random() * 1000) % 10;
				}
			}
			sc.close();
			return arr;
		}
	}
}
