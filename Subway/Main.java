import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.IllegalThreadStateException;
import java.lang.Process;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Pattern;

public class Main {

	// NEVER modify these values!
	private static final int TIME_CHANGING_LANES = 5;
	private static final long sec = 1000000000L, msec = 1000000L;
	private static final Subway is_subway_class = null;
	private static final long LONG_MAX = 9223372036854775807L;

	// tweak this value (but cautiously) if you're getting TLE.
	// there IS an algorithm which is O((V + E) log V) per query.
	// also this algorithm is (relatively) easy to implement and does not violate the specification at all.
	public static final long limit = 100 * sec;

	public static void main(String[] args) {
		encodingCheck();
		new Main().start(args);
	}

	public static void encodingCheck() {
		BufferedReader br = null;
		String s;
		try {
			br = new BufferedReader(new FileReader("enc.chk"));
			s = br.readLine();
		} catch (IOException ioe) {
			System.out.println("Warning: encoding check skipped.");
			return;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException ioe) {
			}
		}
		if (!s.equals("키파체커")) {
			try {
				new Auxiliary().change2NativeEncoding();
				new Auxiliary().change2NativeEncoding("enc.chk");
			} catch (IOException ioe) {
				System.out.println("Warning: encoding changing failed.");
			}
		}
	}

	public void start(String[] list) {
		int correct = 0, total = 0;
		File in, out;
		long mx = -1;
		StringBuilder result = new StringBuilder();
		try {
			ArrayList<StringPair> fileList = retrieveFileList();
			if (list.length > 0) {
				HashSet<String> hs = new HashSet<String>(Arrays.asList(list));
				int i = 0;
				while (i < fileList.size()) {
					if (hs.contains(fileList.get(i).first)) {
						++i;
					} else {
						fileList.remove(i);
					}
				}
			}
			for (StringPair bar : fileList) {
				++total;
				String testName = bar.first, infoName = bar.second;
				System.out.print("\rtesting... (" + total + " / " + fileList.size() + ")");
				try {
					long now = execute("java -Xmx1G Subway info/" + infoName + ".in", "data/" + testName + ".in", "y.out", "y.err");
					mx = now > mx ? now : mx;
					outputCheck("y.out", "data/" + testName + ".out", "info/" + infoName + ".aux");
					outputCheck("y.err");
					++correct;
				} catch (WrongAnswer wa) {
					result.append(testName);
					result.append(": Wrong answer - ");
					result.append(wa.getMessage());
					result.append("\n");
				} catch (WrongOutputFormat wof) {
					result.append(testName);
					result.append(": Wrong Output Format - ");
					result.append(wof.getMessage());
					result.append("\n");
				} catch (TimeLimitExceeded tle) {
					result.append(testName);
					result.append(": Time Limit Exceeded.\n");
				} catch (ReturnValueNotZero rvnz) {
					result.append(testName);
					result.append(": Runtime Error.\n");
				} catch (StreamUsed su) {
					result.append(testName);
					result.append(": Error Stream Used.\n");
				}
			}
		} catch (IOException err) {
			System.err.println("Testing abruptly terminated due to following IOException:");
			err.printStackTrace();
			return;
		}
		if (total == 0) {
			System.out.println("No test cases found!");
			System.out.println("Perhaps missing \"list.kipa\" or \"data\" folder?");
		} else if (total == correct) {
			System.out.println("\rASTOUNDING PERFECT SCORE! (" + correct + " out of " + total + ", " + (mx / msec) + "ms)");
		} else {
			System.out.println("\r" + (correct * 100 / (double)total) + " / 100 (" + correct + " out of " + total + ", " + (mx / msec) + "ms)");
			System.out.print(result);
		}
	}

	public ArrayList<StringPair> retrieveFileList() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("list.kipa"));
		ArrayList<StringPair> res = new ArrayList<StringPair>();
		String s;
		while ((s = br.readLine()) != null) {
			String[] arr = s.trim().split(" ");
			if (arr.length == 2 && !s.startsWith("#")) {
				res.add(new StringPair(arr[0], arr[1]));
			}
		}
		return res;
	}

	public long execute(String cmd, String ifile, String ofile, String efile) throws TimeLimitExceeded, ReturnValueNotZero, IOException {
		ProcessBuilder pb = new ProcessBuilder(Arrays.asList(cmd.split(" ")));
		pb.directory(new File("."));
		if (ifile.length() > 0) {
			pb.redirectInput(new File(ifile));
		}
		pb.redirectOutput(new File(ofile));
		pb.redirectError(new File(efile));
		Process p = pb.start();
		long st = System.nanoTime(), ed;
		while ((ed = System.nanoTime()) - st < limit) {
			try {
				if (p.exitValue() == 0) {
					return ed - st;
				}
				throw new ReturnValueNotZero();
			} catch (IllegalThreadStateException err) {
			}
		}
		p.destroy();
		throw new TimeLimitExceeded();
	}

	public void outputCheck(String afile) throws StreamUsed, IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(afile)));
		String s;
		if ((s = br.readLine()) != null) {
			throw new StreamUsed();
		}
		br.close();
	}

	public void outputCheck(String yfile, String ofile, String auxfile) throws WrongAnswer, WrongOutputFormat, IOException {
		BufferedReader you = new BufferedReader(new FileReader(new File(yfile)));
		BufferedReader flag = new BufferedReader(new FileReader(new File(ofile)));
		BufferedReader aux = new BufferedReader(new FileReader(new File(auxfile)));
		String s;
		HashMap<StringPair, Info> edges = new HashMap<StringPair, Info>();
		while ((s = aux.readLine()) != null) {
			String[] arr = s.split(" ");
			if (arr.length == 4) {
				try {
					edges.get(new StringPair(arr[0], arr[1])).edges.put(arr[3], Long.parseLong(arr[2]));
				} catch (NullPointerException npe) {
					Info now = new Info();
					now.edges.put(arr[3], Long.parseLong(arr[2]));
					edges.put(new StringPair(arr[0], arr[1]), now);
				}
			}
		}
		aux.close();
		while ((s = flag.readLine()) != null) {
			String[] arr = s.split(" ");
			if (arr.length == 3) {
				String startStation = arr[0], endStation = arr[1];
				int totalChanged = -1;
				long totalElapsed = -1;
				if (arr[2].contains(";")) {
					arr = arr[2].split(";");
					totalChanged = Integer.parseInt(arr[0]);
					totalElapsed = Long.parseLong(arr[1]);
				} else {
					totalElapsed = Long.parseLong(arr[2]);
				}
				String pathLong = you.readLine();
				if (totalElapsed != Long.parseLong(you.readLine())) {
					throw new WrongAnswer("total elapsed time differ, " + startStation + " -> " + endStation);
				}
				if (!pathLong.trim().equals(pathLong)) {
					throw new WrongOutputFormat("path output format, " + startStation + " -> " + endStation);
				}
				String[] stations = pathLong.split(" ");
				LaneMinimum lastNum = null;
				String lastStation = stations[0];
				boolean changing = false;
				int i, numChanged = 0;

				if (lastStation.startsWith("[") && lastStation.endsWith("]")) {
					throw new WrongAnswer("changing lanes at head, " + startStation + " -> " + endStation);
				}
				for (i=1; i<stations.length; ++i) {
					String nowStation = stations[i];
					boolean nextChanging = false;
					if (nowStation.startsWith("[") && nowStation.endsWith("]")) {
						nowStation = nowStation.substring(1, nowStation.length() - 1);
						nextChanging = true;
					}
					Info nowEdge = edges.get(new StringPair(lastStation, nowStation));
					if (nowEdge == null) {
						throw new WrongAnswer("no path: " + lastStation + " to " + nowStation + ", " + startStation + " -> " + endStation);
					}
					try {
						if (changing) {
							lastNum.change(nowEdge.edges);
							++numChanged;
						} else {
							lastNum.merge(nowEdge.edges);
						}
					} catch (NullPointerException npe) {
						// the very first case
						lastNum = new LaneMinimum(nowEdge.edges);
					}
					if (lastNum.isEmpty()) {
						throw new WrongAnswer("wrong transition: " + lastStation + " to " + nowStation + ", " + startStation + " -> " + endStation);
					}
					changing = nextChanging;
					lastStation = nowStation;
				}
				long realElapsed = lastNum.retrieveMinimum();
				if (changing) {
					throw new WrongAnswer("changing lanes at tail, " + startStation + " -> " + endStation);
				}
				if (realElapsed != totalElapsed) {
					throw new WrongAnswer(totalElapsed + "(total elapsed time) != " + realElapsed + "(real elapsed time), " + startStation + " -> " + endStation);
				}
			}
		}
		while ((s = you.readLine()) != null) {
			if (!s.equals("")) {
				throw new WrongOutputFormat("extra chars");
			}
		}
		you.close();
		flag.close();
	}

	private class StringPair implements Comparable<StringPair> {

		public String first, second;

		public StringPair(String a, String b) {
			this.first = a;
			this.second = b;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof StringPair) {
				StringPair other = (StringPair)o;
				return this.compareTo(other) == 0;
			}
			return false;
		}

		@Override
		public int compareTo(StringPair other) {
			int compare = this.first.compareTo(other.first);
			return compare == 0 ? this.second.compareTo(other.second) : compare;
		}

		@Override
		public int hashCode() {
			return this.first.hashCode() ^ this.second.hashCode();
		}

	}

	private class Info {
		public HashMap<String, Long> edges;

		public Info() {
			this.edges = new HashMap<String, Long>();
		}

	}

	private class LaneMinimum {
		private HashMap<String, Long> minElapsed;

		public LaneMinimum() {
			this.minElapsed = new HashMap<String, Long>();
		}

		public LaneMinimum(Map<String, Long> init) {
			this.minElapsed = new HashMap<String, Long>(init);
		}

		public void merge(HashMap<String, Long> other) {
			Set<String> keySet = minElapsed.keySet();
			keySet.retainAll(other.keySet());

			for (String s : keySet) {
				minElapsed.put(s, minElapsed.get(s) + other.get(s));
			}
		}

		public void change(HashMap<String, Long> other) {
			// fuck...
			if (minElapsed.size() == 1) {
				String only = null;
				long elapsed = 0;
				for (String s : minElapsed.keySet()) {
					only = s;
					elapsed = minElapsed.get(only);
				}

				this.minElapsed = new HashMap<String, Long>(other);
				this.minElapsed.remove(only);
				for (String s : minElapsed.keySet()) {
					minElapsed.put(s, minElapsed.get(s) + TIME_CHANGING_LANES + elapsed);
				}
			} else {
				String[] min2 = new String[2];
				long[] elapsed2 = new long[] {LONG_MAX, LONG_MAX};
				for (String s : minElapsed.keySet()) {
					long nowElapsed = minElapsed.get(s);
					if (elapsed2[0] > nowElapsed) {
						min2[0] = s;
						elapsed2[0] = nowElapsed;
					}
				}
				for (String s : minElapsed.keySet()) {
					long nowElapsed = minElapsed.get(s);
					if (s.equals(elapsed2[0])) {
						continue;
					}
					if (elapsed2[1] > nowElapsed) {
						min2[1] = s;
						elapsed2[1] = nowElapsed;
					}
				}

				this.minElapsed = new HashMap<String, Long>(other);
				for (String s : minElapsed.keySet()) {
					minElapsed.put(s, minElapsed.get(s) + TIME_CHANGING_LANES + (s.equals(min2[0]) ? elapsed2[1] : elapsed2[0]));
				}
			}
		}

		public long retrieveMinimum() {
			long min = LONG_MAX;
			for (String s : minElapsed.keySet()) {
				long temp = minElapsed.get(s);
				min = min < temp ? min : temp;
			}
			return min;
		}

		public boolean isEmpty() {
			return minElapsed.isEmpty();
		}

	}

	private class TimeLimitExceeded extends Exception {
		private static final long serialVersionUID = 2542190129500675285L;
	}

	private class WrongAnswer extends Exception {
		private static final long serialVersionUID = 8577061020349964323L;
		public WrongAnswer(String msg) {
			super(msg);
		}
	}

	private class ReturnValueNotZero extends Exception {
		private static final long serialVersionUID = 5766043135933461723L;
	}

	private class WrongOutputFormat extends Exception {
		private static final long serialVersionUID = 8054364557948275280L;
		public WrongOutputFormat(String msg) {
			super(msg);
		}
	}

	private class StreamUsed extends Exception {
		private static final long serialVersionUID = 1919142921113320688L;
	}

}

