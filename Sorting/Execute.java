import java.util.Random;

public class Execute {

	public static void main(String[] args) throws Exception {
		try {
			if (args.length == 3 && args[1].startsWith("Special")) {
				int[] arr = null;
				if (args[1].equalsIgnoreCase("SpecialSmallData")) {
					arr = new int[] {-2147483647, -1, 1, 2147483647};
					new Execute().runMany(arr, args[0].charAt(0), Integer.parseInt(args[2]));
				} else if (args[1].equalsIgnoreCase("SpecialRandomizedMany")) {
					Execute exe = new Execute();
					int i, j, n = Integer.parseInt(args[2]);
					char sort_method = args[0].charAt(0);
					arr = new int[100];
					for (i=0; i<n; ++i) {
						Random r = new Random();
						for (j=0; j<100; ++j) {
							arr[j] = r.nextInt();
						}
						exe.run(arr, sort_method);
					}
				}
			} else if (args.length == 3) {
				int i, n = Integer.parseInt(args[2]);
				int[] arr = new int[n];
				if (args[1].equalsIgnoreCase("Random")) {
					Random r = new Random();
					for (i=0; i<n; ++i) {
						arr[i] = r.nextInt();
					}
				} else if (args[1].equalsIgnoreCase("2...n1")) {
					for (i=0; i<n; ++i) {
						arr[i] = (i == n - 1) ? 1 : (i + 2);
					}
				} else if (args[1].equalsIgnoreCase("Sorted")) {
					for (i=0; i<n; ++i) {
						arr[i] = i + 1;
					}
				} else if (args[1].equalsIgnoreCase("AllSame")) {
					for (i=0; i<n; ++i) {
						arr[i] = 20180510;
					}
				}
				long fm = Runtime.getRuntime().freeMemory();
				new Execute().run(arr, args[0].charAt(0));
				System.out.println(fm - Runtime.getRuntime().freeMemory());
			}
		} catch (Exception err) {
			System.out.println("Something went wrong.");
			System.out.println("Stack Trace:");
			err.printStackTrace();
			throw err;
		}
	}

	public void run(int[] iarr, char c) throws Exception {
		int[] res = null;
		switch (c) {
			case 'B':
				res = SortingTest.DoBubbleSort(iarr);
				break;
			case 'I':
				res = SortingTest.DoInsertionSort(iarr);
				break;
			case 'H':
				res = SortingTest.DoHeapSort(iarr);
				break;
			case 'M':
				res = SortingTest.DoMergeSort(iarr);
				break;
			case 'Q':
				res = SortingTest.DoQuickSort(iarr);
				break;
			case 'R':
				res = SortingTest.DoRadixSort(iarr);
				break;
		}
		int i, len = iarr.length;
		for (i=1; i<len; ++i) {
			if (res[i-1] > res[i]) {
				throw new Exception("Wrong Sorting");
			}
		}
	}

	public void runMany(int[] iarr, char c, int n) throws Exception {
		int i;
		switch (c) {
			case 'B':
				for (i=0; i<n; ++i) {
					SortingTest.DoBubbleSort(iarr);
				}
				break;
			case 'I':
				for (i=0; i<n; ++i) {
					SortingTest.DoInsertionSort(iarr);
				}
				break;
			case 'H':
				for (i=0; i<n; ++i) {
					SortingTest.DoHeapSort(iarr);
				}
				break;
			case 'M':
				for (i=0; i<n; ++i) {
					SortingTest.DoMergeSort(iarr);
				}
				break;
			case 'Q':
				for (i=0; i<n; ++i) {
					SortingTest.DoQuickSort(iarr);
				}
				break;
			case 'R':
				for (i=0; i<n; ++i) {
					SortingTest.DoRadixSort(iarr);
				}
				break;
		}
	}

}
