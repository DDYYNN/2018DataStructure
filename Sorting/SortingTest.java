import java.io.*;
import java.util.*;
import java.util.Queue;

public class SortingTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try
		{
			boolean isRandom = false;	// 입력받은 배열이 난수인가 아닌가?
			int[] value;	// 입력 받을 숫자들의 배열
			String nums = br.readLine();	// 첫 줄을 입력 받음
			if (nums.charAt(0) == 'r')
			{
				// 난수일 경우
				isRandom = true;	// 난수임을 표시

				String[] nums_arg = nums.split(" ");

				int numsize = Integer.parseInt(nums_arg[1]);	// 총 갯수
				int rminimum = Integer.parseInt(nums_arg[2]);	// 최소값
				int rmaximum = Integer.parseInt(nums_arg[3]);	// 최대값

				Random rand = new Random();	// 난수 인스턴스를 생성한다.

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 각각의 배열에 난수를 생성하여 대입
					value[i] = rand.nextInt(rmaximum - rminimum + 1) + rminimum;
			}
			else
			{
				// 난수가 아닐 경우
				int numsize = Integer.parseInt(nums);

				value = new int[numsize];	// 배열을 생성한다.
				for (int i = 0; i < value.length; i++)	// 한줄씩 입력받아 배열원소로 대입
					value[i] = Integer.parseInt(br.readLine());
			}

			// 숫자 입력을 다 받았으므로 정렬 방법을 받아 그에 맞는 정렬을 수행한다.
			while (true)
			{
				int[] newvalue = (int[])value.clone();	// 원래 값의 보호를 위해 복사본을 생성한다.

				String command = br.readLine();

				long t = System.currentTimeMillis();
				switch (command.charAt(0))
				{
					case 'B':	// Bubble Sort
						newvalue = DoBubbleSort(newvalue);
						break;
					case 'I':	// Insertion Sort
						newvalue = DoInsertionSort(newvalue);
						break;
					case 'H':	// Heap Sort
						newvalue = DoHeapSort(newvalue);
						break;
					case 'M':	// Merge Sort
						newvalue = DoMergeSort(newvalue);
						break;
					case 'Q':	// Quick Sort
						newvalue = DoQuickSort(newvalue);
						break;
					case 'R':	// Radix Sort
						newvalue = DoRadixSort(newvalue);
						break;
					case 'X':
						return;	// 프로그램을 종료한다.
					default:
						throw new IOException("잘못된 정렬 방법을 입력했습니다.");
				}
				if (isRandom)
				{
					// 난수일 경우 수행시간을 출력한다.
					System.out.println((System.currentTimeMillis() - t) + " ms");
				}
				else
				{
					// 난수가 아닐 경우 정렬된 결과값을 출력한다.
					for (int i = 0; i < newvalue.length; i++)
					{
						System.out.println(newvalue[i]);
					}
				}

			}
		}
		catch (IOException e)
		{
			System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoBubbleSort(int[] value)
	{
		// modification from Discrete Mathematics and its Applications, K.H.Rosen, 7th ed.
		int N = value.length;
		for(int i = 0; i < N-1; i++){
			for(int j = 0; j < N - i - 1; j++){
				if(value[j] > value[j+1])
					swap(value, j, j+1);		// swap 함수는 코드의 맨 마지막에 정의되어 있다.
			}
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoInsertionSort(int[] value)
	{
		// modification from Discrete Mathematics and its Applications, K.H.Rosen, 7th ed.
		for(int j = 1; j < value.length; j++){
			int i = 0;
			int tmp = value[j];
			for(; tmp > value[i]; i++);			// j번째 값이 들어갈 위치를 찾아 i에 저장한다.
			for(int k = j - 1; k >= i; k--){	// i번째 이후 값들은 한 칸씩 shift하여 value[j]가 들어갈 공간을 만든다.
				value[k + 1] = value[k];
			}
			value[i] = tmp;						// i번째 공간에 value[j] 삽입.
		}
		return (value);
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoHeapSort(int[] value)
	{
		// modification from Data Abstraction & Problem Solving with JAVA: Walls and Mirrors, 
		// 					 J.J.Prichard, 3rd ed.
		int N = value.length;
		for(int i = (N + 1)/2; i >= 0; i--)
			percolateDown(value, i, N);			// 우선 전체적인 수선으로 Heap 구조를 만든다.
		for(int size = N - 2; size >= 0; size--){
			swap(value, 0, size + 1);			// 맨 마지막 원소를 하나씩 빼면서
			percolateDown(value, 0, size);		// Heap 구조에 맞게 수선한다.
		}
		return (value);
	}
	
	private static void percolateDown(int[] value, int i, int n){
		// Heap 구조에 맞게 "수선"하는 함수.
		int leftChild = 2*i + 1;
		int rightChild = 2*i + 2;
		if(leftChild < n){
			if ((rightChild < n) && (value[leftChild] < value[rightChild])) {
				leftChild = rightChild;  
			}
			if (value[i] < value[leftChild]) {
				swap(value, i, leftChild);
				percolateDown(value, leftChild, n);
			}

		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoMergeSort(int[] value)
	{
		int N = value.length;
		if(N > 1){
			int mid = N / 2;
			int[] L = new int[mid];
			int[] R = new int[N - mid];
			System.arraycopy(value, 0, L, 0, mid);				// value의 앞 절반을 L에 복사
			System.arraycopy(value, mid, R, 0, N - mid);		// value의 뒷 절반을 R에 복사
			value = merge(DoMergeSort(L), DoMergeSort(R));
		}
		return (value);
	}
	
	private static int[] merge(int[] L, int[] R){
		int leftLen = L.length, rightLen = R.length;
		int[] M = new int[leftLen + rightLen];
		int i = 0, j = 0, k = 0;
		while(i < leftLen && j < rightLen){   // L과 R을 비교해 작은 원소 순서대로 M에 넣는다.
			if(L[i] <= R[j]){
				M[k] = L[i];
				i++;
			}
			else{
				M[k] = R[j];
				j++;
			}
			k++;
		}
		
		// 비교 후 남은 원소들을 한번에 M에 넣는 과정.
		if(i < leftLen)			// L이 남았을 때
			for(; i < leftLen ; M[k++] = L[i++]);
		else if(j < rightLen)	// R이 남았을 때
			for(; j < rightLen ; M[k++] = R[j++]);
		
		return M;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoQuickSort(int[] value)
	{
		quickSort(value, 0, value.length-1);	// sorting의 양 끝값을 인자로 받는 함수를 새로 정의한다.
												// 이는 quickSort가 in-place sorting을 할 수 있게 하기 위함이다.
		return (value);
	}
	
	private static void quickSort(int[] value, int first, int last){
		if(first < last){
			int pivotIdx = partition(value, (first + last)/2, first, last);		// pivot은 중앙에 있는 원소로 설정한다.
			quickSort(value, first, pivotIdx - 1);		// pivot 앞부분에 대한 recursive sorting
			quickSort(value, pivotIdx + 1, last);		// pivot 뒷부분에 대한 recursive sorting
		}
	}
	
	private static int partition(int[] value, int pivotIdx, int first, int last){
		int pivotVal = value[pivotIdx];
		swap(value, pivotIdx, last);		// pivot을 맨 뒤에 두고 시작한다.
		int	lastOfLess = first - 1, lastOfGreater = first - 1;
		// lastOfLess는 pivot보다 작은 원소 중 가장 뒤의 원소의 위치,
		// lastOfGreater는 pivot보다 큰 원소 중 가장 뒤의 원소의 위치를 나타낸다.
		// lastOfGreater + 1은 분류되지 않은 원소 중 가장 처음 원소의 위치이다.
		while(lastOfGreater < last){
			if(value[lastOfGreater + 1] > pivotVal){
				lastOfGreater++;
			}
			else{
				swap(value, lastOfLess + 1, lastOfGreater + 1);
				lastOfLess++;
				lastOfGreater++;
			}
		}
		return lastOfLess;	// partition 후 pivot의 위치는 lastOfLess이다.
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static int[] DoRadixSort(int[] value)
	{
		Queue<Integer> bucket[] = new LinkedList[20];
		// j번째 자릿수에 대해 stable sort를 진행하기 위한 bucket.
		// Stable sort를 만족하기 위해 FIFO 방식인 queue를 이용하여 bucket을 구현한다.
		// 음수도 정렬하기 위해 radix는 1~19를 이용한다. 즉 bucket의 크기를 20으로 한다.
		int i, j;
		for(i = 1; i < 20; i++){
			bucket[i] = new LinkedList<Integer>();
		}
		int maxDig = maxDigits(value);			// maxDig = 가장 긴 수의 자릿수
		int divByTens = 1;
		for(int digit = 0; digit < maxDig; digit++){
			for(j = 0; j < value.length; j++){
				int radix = (value[j] / divByTens) % 10 + 10;
				// 음수 나머지가 -9 ~ -1 범위에서 발생할 수 있으므로, 나머지에 무조건 10을 더한다.
				// 즉 양수 나머지는 radix가 10보다 큰 값을 가지게 된다.
				bucket[radix].offer(value[j]);
			}
			divByTens *= 10;
			for(j = 0, i = 1; i < 20; i++){
				while(!bucket[i].isEmpty()){
					value[j++] = bucket[i].poll();
					// bucket에 넣은 순서대로 poll되므로 Stable sort가 이루어진다.
				}
			}
		}
		return (value);
	}
	
	private static int maxDigits(int[] value){
		// value의 원소 중 가장 긴 원소의 자릿수를 구하는 함수.
		// 절댓값이 가장 큰 원소를 찾아 그 자릿수를 구한다.
		int max = Math.abs(value[0]);
		for(int i = 1; i < value.length; i++){
			int tmp = Math.abs(value[i]);
			if(tmp > max) max = tmp;
		}
		int maxLen = 1;
		for(; max >= 10; max /= 10, maxLen++);
		return maxLen;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	private static void swap(int[] arr, int idx1, int idx2){
		// arr배열의 idx1번째 원소와 idx2번째 원소를 바꾸는 함수.
		int tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
	}
}
