import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.IllegalThreadStateException;
import java.lang.Process;

public class Test {

	public static final long sec = 1000000000L, msec = 1000000L;
	public static final long limit = 10 * sec;

	public static void main(String[] args) {
		int i;
		File in, out;
		long mx = -1;
		for (i=1; (in = new File("data/i" + i + ".in")).exists() && (out = new File("data/o" + i + ".out")).exists(); ++i) {
			try {
				System.out.print("\rtest case #" + i + "...");
				ProcessBuilder pb = new ProcessBuilder("java", "-Xms128M", "-Xmx128M", "Matching");
				pb.directory(new File("."));
				pb.redirectInput(in);
				pb.redirectErrorStream(true);
				pb.redirectOutput(new File("y.out"));
				Process p = pb.start();
				long st = System.nanoTime(), ed;
				while ((ed = System.nanoTime()) - st < limit) {
					try {
						if (p.exitValue() != 0) {
							System.out.println("\rRuntime error on test " + i + ". Cannot continue testing.");
							return;
						} else {
							break;
						}
					} catch (IllegalThreadStateException err) {
					}
				}
				mx = ed - st > mx ? ed - st : mx;
				if (ed - st >= limit) {
					System.out.println("\rTime limit exceeded on test " + i + ". Cannot continue testing.");
					System.out.println("Your Runtime : " + (ed-st)/(1000000) + "ms");
					p.destroy();
					return;
				}
				System.out.println("Your Runtime : " + (ed-st)/(1000000) + "ms");
				BufferedReader br = new BufferedReader(new FileReader(new File("y.out")));
				BufferedReader br2 = new BufferedReader(new FileReader(out));
				String s, s2;
				int l = 0;
				while ((s = br.readLine()) != null) {
					s2 = br2.readLine();
					l++;
					if (!s.equals(s2)) {
						System.out.println("\rWrong answer on test " + i + ". Cannot continue testing.");
						System.out.println("[Line " + l + "]\rYour answer: \n" + s);
						System.out.println("\rExact answer: \n" + s2);
						break;
					}
				}
			} catch (IOException err) {
				err.printStackTrace();
				return;
			}
		}
		if (--i == 0) {
			System.out.println("No test cases found!");
			System.out.println("Perhaps missing \"data\" folder?");
		} else {
			System.out.println("\rYou passed all " + i + " test case" + (i == 1 ? "" : "s") + "! (" + (mx / msec) + "ms)");
		}
	}

}

