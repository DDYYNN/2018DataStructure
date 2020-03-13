import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.IllegalThreadStateException;
import java.lang.Process;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Main {

	public static final long sec = 1000000000L, msec = 1000000L;
	public static final long limit = 5 * sec;
	public static final int test_size = 1000000;

	public static final String execute = "Execute";

	public static final String[] sorting_str = new String[] {
		null, "Bubble", "Insertion", null, "Quick", "Radix", null, "Heap", "Merge"
	};

	public static int returning_value;

	public static void main(String[] args) {
		try {
			new Main().start();
		} catch (Exception err) {
			System.out.println("Something went wrong.");
			System.out.println("Stack Trace:");
			err.printStackTrace();
		}
	}

	public int judgeSortingMethod(String s) throws IOException {
		int flag = 0;
		String size_str = Integer.toString(test_size);
		execute(new ProcessBuilder("java", "-Xms1G", "-Xmx1G", execute, s, "Random", size_str));
		long usedMemory = getLongFromFile("y.out");
		if (returning_value == 0xCAFE) {		// Bubble or Insertion
			execute(new ProcessBuilder("java", execute, s, "2...n1", size_str));
			if (returning_value == 0xCAFE) {	// Bubble
				flag = 1;
				execute(new ProcessBuilder("java", execute, s, "Sorted", size_str));
				if (returning_value == 0) {		// Optimized Bubble
					flag |= 128;
				} else if (returning_value != 0xCAFE) {
					return -1;
				}
			} else if (returning_value == 0) {	// Insertion
				flag = 2;
			} else {
				return -1;
			}
		} else if (returning_value != 0) {
			return -1;
		} else if (usedMemory < test_size * 2) {	// Quick or Heap
			execute(new ProcessBuilder("java", execute, s, "Sorted", size_str));
			flag = 196;
			if (returning_value == 0xCAFE) {		// Unrandomized Quick
				flag &= 132;
			} else if (returning_value != 0) {
				return -1;
			}
			execute(new ProcessBuilder("java", execute, s, "AllSame", size_str));
			if (returning_value == 0xCAFE) {		// Unoptimized Quick
				flag &= 68;
			} else if (returning_value != 0) {
				return -1;
			}
			// I do not consider Randomized and Optimized Quick Sort
			// Sorry!
			if (flag == 196) {
				flag = 16;
			}
		} else {								// Merge or Radix
			execute(new ProcessBuilder("java", execute, s, "SpecialSmallData", "10000000"));
			if (returning_value == 0xCAFE) {	// Radix
				flag = 32;
			} else if (returning_value == 0) {	// Merge
				flag = 8;
			} else {
				return -1;
			}
		}
		return flag;
	}

	public void start() throws Exception {
		final String[] all = new String[] {"Bubble", "Insertion", "Quick", "Merge", "Heap", "Radix"};
		final double[] time = new double[] {3., 1.5, 2.5, 1.5, 2, 1.5};
		final String[] opran = new String[] {"", " Randomized", " Optimized", " Optimized and Randomized"};
		int i, j;
		for (i=0; i<6; ++i) {
			System.out.println("=== " + all[i] + " Sort (" + (limit * time[i] / sec) + " sec) ===");
			for (j=0; j<10; ++j) {
				System.out.print("\rSanity Check... (" + (j + 1) + " / 10)");
				execute(new ProcessBuilder("java", execute, all[i], "SpecialRandomizedMany", "10000"));
				if (returning_value != 0) {
					System.out.println("Sanity Check Failed!");
					break;
				}
			}
			System.out.print(" Okay.\nClassification...");
			if (j == 10) {
				int u = judgeSortingMethod(all[i].substring(0, 1));
				if (u < 0) {
					System.out.println(" Wrong Sorting Result!");
				} else if ((u & 63) == (1 << i)) {
					System.out.println(" It really is" + opran[u >>> 6] + " " + sorting_str[(u & 63) % 9] + " Sort!");
				} else {
					System.out.println(" I think it is" + opran[u >>> 6] + " " + sorting_str[(u & 63) % 9] + " Sort...");
				}
			}
		}
	}

	private long execute(ProcessBuilder pb) throws IOException {
		return execute(pb, "", "y.out", "y.err", limit);
	}

	private long execute(ProcessBuilder pb, String in, String out, String err, long mxlim) throws IOException {
		pb.directory(new File("."));
		if (in.length() != 0) {
			pb.redirectInput(new File(in));
		}
		pb.redirectOutput(new File(out));
		pb.redirectError(new File(err));
		Process p = pb.start();
		long st = System.nanoTime(), ed;
		returning_value = 0xCAFE;
		while ((ed = System.nanoTime()) - st < limit) {
			try {
				returning_value = p.exitValue();
				break;
			} catch (IllegalThreadStateException exc) {
				// pass;
			}
		}
		p.destroy();
		long elapsed = ed - st;
		return elapsed > mxlim ? mxlim : elapsed;
	}

	public long getLongFromFile(String fn) throws IOException {
		try {
			BufferedReader br = new BufferedReader(new FileReader(fn));
			long res = Long.parseLong(br.readLine());
			br.close();
			return res;
		} catch (NumberFormatException nfe) {
			return -1;
		}
	}

}
