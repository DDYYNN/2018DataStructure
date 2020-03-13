import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Auxiliary {

	public HashMap<String, String> flag2name, flag2num;
	public HashMap<String, LinkedList<String> > edges, revEdges;

	public Auxiliary() {
		flag2name = new HashMap<String, String>();
		flag2num = new HashMap<String, String>();
		edges = new HashMap<String, LinkedList<String> >();
		revEdges = new HashMap<String, LinkedList<String> >();
	}

	public void change2NativeEncoding() {
		for (File f : new File("./data").listFiles()) {
			String s = f.getName();
			if (s.endsWith(".in") || s.endsWith(".out")) {
				try {
					change2NativeEncoding("./data/" + s);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		for (File f : new File("./info").listFiles()) {
			String s = f.getName();
			if (s.endsWith(".in") || s.endsWith(".aux")) {
				try {
					change2NativeEncoding("./info/" + s);
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	public void change2NativeEncoding(String fn) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fn), "utf-8"));
		ArrayList<String> al = new ArrayList<String>();
		String s;
		while ((s = br.readLine()) != null) {
			for (char c : s.toCharArray()) {
				if (!(c <= 128 || 44032 <= c && c < 55204)) {
					throw new UnsupportedEncodingException();
				}
			}
			al.add(s);
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter(fn));
		for (String line : al) {
			bw.write(line);
			bw.write("\n");
		}
		bw.close();
	}

	public static void main(String[] args) {
		int i;
		if (args.length == 0) {
			new Auxiliary().change2NativeEncoding();
		} else for (i=0; i<args.length; ++i) {
			try {
				if (args[i].contains("?")) {
					String[] arr = args[i].split("\\?");
					new Auxiliary().makeOutFile(arr[0], arr[1]);
				} else {
					new Auxiliary().makeAUXFile(args[i]);
				}
			} catch (IOException ioe) {
				System.err.println("Error on " + args[i] + ".");
				ioe.printStackTrace();
			} catch (WrongFormatException wfe) {
				System.err.println("Error on " + args[i] + ".");
				wfe.printStackTrace();
			}
		}
	}

	public void makeAUXFile(String filename) throws IOException, WrongFormatException {
		String outfile = filename.replaceAll("^(.*)(?:\\.[a-zA-Z0-9]+)$", "$1.aux");
		if (outfile.equals(filename)) {
			outfile += ".aux";
		}

		BufferedReader br = null;
		BufferedWriter bw = null;
		String s;
		try {
			br = new BufferedReader(new FileReader(filename));
			bw = new BufferedWriter(new FileWriter(outfile));

			while (!(s = br.readLine()).equals("")) {
				String[] arr = s.split(" ");
				flag2name.put(arr[0], arr[1]);
				flag2num.put(arr[0], arr[2]);
			}

			ArrayList<String> edgesFile = new ArrayList<String>();
			while (null != (s = br.readLine()) && !s.equals("")) {
				String[] arr = s.split(" ");
				try {
					edges.get(arr[0]).add(arr[1]);
				} catch (NullPointerException npe) {
					LinkedList<String> ll = new LinkedList<String>();
					ll.add(arr[1]);
					edges.put(arr[0], ll);
				}
				try {
					revEdges.get(arr[1]).add(arr[0]);
				} catch (NullPointerException npe) {
					LinkedList<String> ll = new LinkedList<String>();
					ll.add(arr[0]);
					revEdges.put(arr[1], ll);
				}
				edgesFile.add(s);
			}
			HashSet<String> flagFound = new HashSet<String>();
			int count = 0;
			for (String flag : flag2name.keySet()) {
				if (!flagFound.contains(flag)) {
					Queue<String> q = new LinkedList<String>();
					q.add(flag);
					flagFound.add(flag);
					String nowLane = flag2num.get(flag);
					HashSet<String> changingCheck = new HashSet<String>();
					++count;
					while (!q.isEmpty()) {
						String nowNode = q.remove(), nowName = flag2name.get(nowNode);
						if (!flag2num.get(nowNode).equals(nowLane)) {
							throw new WrongFormatException();
						}
						if (changingCheck.contains(nowName)) {
							throw new WrongFormatException();
						}
						changingCheck.add(nowName);

						flag2num.put(nowNode, String.valueOf(count));
						try {
							for (String u : edges.get(nowNode)) {
								if (!flagFound.contains(u)) {
									flagFound.add(u);
									q.add(u);
								}
							}
						} catch (NullPointerException npe) {
						}
						try {
							for (String u : revEdges.get(nowNode)) {
								if (!flagFound.contains(u)) {
									flagFound.add(u);
									q.add(u);
								}
							}
						} catch (NullPointerException npe) {
						}
					}
				}
			}
			for (String line : edgesFile) {
				String[] arr = line.split(" ");
				String num1 = flag2num.get(arr[0]), num2 = flag2num.get(arr[1]);
				String name1 = flag2name.get(arr[0]), name2 = flag2name.get(arr[1]);
				long time = Long.parseLong(arr[2]);
				if (num1 != null && num2 != null && name1 != null && name2 != null && time >= 0 && num1.equals(num2)) {
					bw.write(name1);
					bw.write(" ");
					bw.write(name2);
					bw.write(" ");
					bw.write(String.valueOf(time));
					bw.write(" ");
					bw.write(num1);
					bw.write("\n");
				} else {
					throw new WrongFormatException();
				}
			}
		} catch (IOException ioe) {
			throw ioe;
		} finally {
			br.close();
			bw.close();
		}
		System.out.println(outfile);
	}

	public void makeOutFile(String in, String out) throws IOException, WrongFormatException {
		String outfile = in.replaceAll("^(.*)(?:\\.[a-zA-Z0-9]+)$", "$1.out");
		if (outfile.equals(out) || outfile.equals(in)) {
			outfile += ".out";
		}

		BufferedReader readIn = new BufferedReader(new FileReader(in));
		BufferedReader readOut = new BufferedReader(new FileReader(out));

		BufferedWriter writeOut = new BufferedWriter(new FileWriter(outfile));

		String s;

		while ((s = readIn.readLine()) != null) {
			String[] arr = s.split(" ");
			if (arr.length == 2 || (arr.length == 3 && arr[2].equals("!"))) {
				boolean cshsgr = arr.length == 3;
				String time = readOut.readLine();
				int timeChanged = -1;
				if (cshsgr) {
					timeChanged = 0;
					char[] chs = time.toCharArray();
					int i;
					for (i=0; i<chs.length; ++i) {
						if (chs[i] == '[') {
							++timeChanged;
						}
					}
				}
				time = readOut.readLine();
				writeOut.write(arr[0]);
				writeOut.write(" ");
				writeOut.write(arr[1]);
				writeOut.write(" ");
				if (cshsgr) {
					writeOut.write(String.valueOf(timeChanged));
					writeOut.write(";");
				}
				writeOut.write(time);
				writeOut.write("\n");
			}
		}

		readIn.close();
		readOut.close();
		writeOut.close();

		System.out.println(outfile);
	}

	public class WrongFormatException extends Exception {
		private static final long serialVersionUID = 7801702799807547914L;
	}

}
