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
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class Main {

	// Okay, for test case #8, change this if you'd like.
	public static final String ENCODING = "utf-8";

	// *Ahem*
	public static long easter_egg = 0;

	// NEVER modify these values!
	public static final long sec = 1000000000L, msec = 1000000L;
	public static final int MAGIC_NUM = 256;
	public static final Matching is_matching_class = null;

	// tweak this value if you're getting TLE.
	// there IS an algorithm which is:
	//   O(n log n) for any "<" command
	//   O(k log n) for any "?" command
	//   O(n) for any "@" command
	// but this algorithm has a probability that it violates the restrictions stated at soar.
	public static final long limit = 10 * sec;

	public static void main(String[] args) {
		try {
			new Main().start(Integer.parseInt(args[0]));
		} catch (IndexOutOfBoundsException ioobe) {
			new Main().start(0);
		}
	}

	public void start(int idx) {
		int i, c = 0;
		File in, out;
		long mx = -1;
		try {
			for (
					i = idx == 0 ? 1 : idx;
					((idx == 0 && i <= 7) || (idx != 0 && i == idx)) &&
					((in = new File("data/i" + i + ".in")).exists() && (out = new File("data/o" + i + ".out")).exists());
					++i
				) {
				System.out.print("\rtest case #" + i + "...");
				try {
					if (((1 << i) & MAGIC_NUM) != 0) {
						tweakCompile();
					}
					long now = execute("java -Xmx1G Matching", "data/i" + i + ".in", "y.out", "y.err");
					mx = now > mx ? now : mx;
					outputCheck("y.out", "data/o" + i + ".out");
					outputCheck("y.err");
				} catch (WrongAnswer wa) {
					System.out.println(" Wrong Answer on test " + i + ".");
					return;
				} catch (WrongOutputFormat wof) {
					System.out.println(" Wrong Output Format on test " + i + ".");
					return;
				} catch (TimeLimitExceeded tle) {
					System.out.println(" Time Limit Exceeded on test " + i + ".");
					return;
				} catch (ReturnValueNotZero rvnz) {
					System.out.println(" Runtime Error on test " + i + ".");
					return;
				} catch (StreamUsed su) {
					System.out.println(" Error Stream Used on test " + i + ".");
					return;
				}
				++c;
			}
		} catch (IOException err) {
			err.printStackTrace();
			return;
		}
		if (c == 0) {
			if (idx == 0) {
				System.out.println("No test cases found!");
				System.out.println("Perhaps missing \"data\" folder?");
			} else {
				System.out.println("Test case number specified incorrectly.");
			}
		} else {
			if (idx == 0) {
				System.out.println("\rYou passed all " + c + " test case" + (c == 1 ? "" : "s") + "! (" + (mx / msec) + "ms)");
			} else {
				System.out.println("\rYou passed test case " + idx + "! (" + (mx / msec) + "ms)");
			}
		}
	}

	public void tweakCompile() throws IOException, TimeLimitExceeded, ReturnValueNotZero {
		File[] filesList = new File(".").listFiles();
		ArrayList<Copier> cps = new ArrayList<>();
		Exception occurred = null;
		for (File f : filesList) {
			String filename = f.getName();
			if (f.isFile() && !filename.equals("Main.java") && !filename.equals("Copier.java") && filename.endsWith(".java")) {
				Copier cp = new Copier(f.getName());
				cp.addRule("(?<![0-9a-zA-Z])100(?![0-9a-zA-Z\\\\])", "20000");
				cp.addRule("(?<![0-9a-zA-Z])6(?![0-9a-zA-Z\\\\])", "20000");
				cp.addRule("(?<![0-9a-zA-Z])5(?![0-9a-zA-Z\\\\])", "19999");
				cp.read();
				cp.write(true);
				cps.add(cp);
			}
		}
		try {
			execute("javac Matching.java -encoding " + ENCODING, "", "y.out", "y.err");
		} catch (IOException err) {
			occurred = err;
		} catch (TimeLimitExceeded err) {
			occurred = err;
		} catch (ReturnValueNotZero err) {
			occurred = err;
		}
		for (Copier cp : cps) {
			cp.write(false);
		}
		if (occurred != null) {
			if (occurred instanceof IOException) {
				throw (IOException)occurred;
			} else if (occurred instanceof TimeLimitExceeded) {
				throw (TimeLimitExceeded)occurred;
			} else if (occurred instanceof ReturnValueNotZero) {
				throw (ReturnValueNotZero)occurred;
			}
		}
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

	public void outputCheck(String afile, String bfile) throws WrongAnswer, WrongOutputFormat, IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(afile)));
		BufferedReader br2 = new BufferedReader(new FileReader(new File(bfile)));
		String s, s2;
		while ((s2 = br2.readLine()) != null) {
			s = br.readLine();
			if (!s2.equals(s)) {
				if (s != null && s2.trim().equals(s.trim())) {
					throw new WrongOutputFormat();
				}
				throw new WrongAnswer();
			}
		}
		if ((s = br.readLine()) != null) {
			throw new WrongAnswer();
		}
		br.close();
		br2.close();
	}

	private class TimeLimitExceeded extends Exception {
	}
	private class WrongAnswer extends Exception {
	}
	private class ReturnValueNotZero extends Exception {
	}
	private class WrongOutputFormat extends Exception {
	}
	private class StreamUsed extends Exception {
	}

	public static boolean check(String s) {
		if (s.length() == 2) {
			char first = s.charAt(0), second = s.charAt(1);
			if (0xAC00 <= first && first < 0xD7A4 && 0xAC00 <= second && second <= 0xD7A4) {
				try {
					MessageDigest sh = MessageDigest.getInstance("SHA-256");
					sh.update(s.getBytes("utf-8"));
					byte[] data = sh.digest();
					int i;
					StringBuilder sb = new StringBuilder();
					for (i=0; i<data.length; ++i) {
						sb.append(Integer.toString(0x100 | ((data[i] ^ first + second) & 255), 16).substring(1));
					}
					if (sb.toString().equals("88a40c5a045ecb958f4e7eae68cec29a75d505e66da384261fc542f6ca7e4e10")) {
						Main.easter_egg = ((long)first << 16) | (long)second;
						return true;
					}
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
		}
		return false;
	}

	public static void r(int a, int b) {
		Main.easter_egg <<= 8;
		Main.easter_egg |= a * b;
		if ((Main.easter_egg & 0xFF0000000000L) != 0) {
			Random r = new Random(Main.easter_egg);
			int i;
			StringBuilder sb = new StringBuilder();
			sb.append("https://site.thekipa.com/charsnine/matching.php?pass=");
			for (i=0; i<a; ++i) {
				sb.append("0123456789abcdefghijklmnopqrstuvwxyz".charAt(r.nextInt(36)));
			}
			System.out.println(sb);
		}
	}

}

