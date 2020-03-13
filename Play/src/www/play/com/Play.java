package www.play.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;



class Play{
	
//	
////	public static int partition(int[] A, int first, int last)
////	// partition array A[first...last]
////	{
////	int pivot = A[first];
////	int lastS1 = first;
////	for (int firstUnknown = first+1; firstUnknown <= last; ++firstUnknown) {
////		if (A[firstUnknown] < pivot) {
////			++lastS1;
////			int tmp = A[firstUnknown];
////			A[firstUnknown] = A[lastS1];
////			A[lastS1] = tmp;// swap
////		} //end if
////	} // end for
////	int tmp = A[first];
////	A[first] = A[lastS1];
////	A[lastS1] = tmp;// swap
////	return lastS1;
////	} //end partition
////	
////	public static void print(int[] A){
////		for(int i = 0; i < A.length; i++){
////			System.out.print(A[i]+", ");
////		}
////	}
////	
////	public static void main(String[] args){
////		int[] arr = {3,1,2,3,7,2,3,4,1,2,3,4,5,7,10,2,1,3,7,7,7};
////		System.out.println(partition(arr,0,arr.length-1));
////		print(arr);
////	}
//	
//	public static void main(String[] args){
//		System.out.println((-142)%10);
//		System.out.println(String.valueOf(Math.abs(-203)).length());
//		String str = "123456789";
//		System.out.println(str.substring(0, 8));
	



//		public static void main(String[] args) throws NumberFormatException, IOException{
//			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//			while(true){
//				System.out.println("input : ");
//				int[] value = new int[3];	// 배열을 생성한다.
//				for (int i = 0; i < 3; i++)	// 한줄씩 입력받아 배열원소로 대입
//					value[i] = Integer.parseInt(br.readLine());
//				double avg = (value[0]+value[1]+value[2])/3;
//				double stdev = 0;
//				for (int i = 0; i < 3; i++){
//					stdev += (value[i] - avg) * (value[i] - avg);
//				}
//				stdev = Math.sqrt(stdev/3);
//				System.out.println(stdev);
//			}
//		}
	
	public static void main(String[] args){
//		HashMap<String, String> h = new HashMap<>();
//		h.put("me", "1");
//		h.put("you", "2");
//		System.out.println(h.get("me") + ", " + h.get("you"));
//		h.put("me", "3");
//		System.out.println(h.get("me") + ", " + h.get("you"));
//		HashMap<String, Integer> m = new HashMap<>();
//		Integer num = m.get("me");
//		System.out.println(num);
//		
//		ArrayList<dumb> a = new ArrayList<>();
//		a.add(new dumb("this"));
//		dumb d = a.get(0);
//		System.out.println(d.str);
//		d.str = "that";
//		System.out.println(a.get(0).str);
//	
		
		dumb[] d = new dumb[5];
		d[0] = new lessdumb("OH");
		d[1] = new moredumb("WOW");
		d[0].thedumb();
		d[1].thedumb();
	}
	
}

class dumb{
	String str;
	public dumb(String str){
		this.str = str;
	}
	
	public void thedumb(){}
}

class moredumb extends dumb{
	public moredumb(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}

	public void thedumb(){
		System.out.println("DUMBDUMB");
	}
}

class lessdumb extends dumb{

	public lessdumb(String str) {
		super(str);
		// TODO Auto-generated constructor stub
	}
	
	public void thedumb(){
		System.out.println("lessdumb.");
	}
}