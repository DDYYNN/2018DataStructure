import java.io.*;
import java.util.*;

public class Util {

	public final String sorting_test = "SortingTest.java";
	public final byte[] bytes_private = "private".getBytes();
	public final byte[] bytes_public = "public".getBytes();

	public static void main(String[] args) {
		try {
			new Util().start();
		} catch (Exception err) {
			System.out.println("Something went wrong.");
			System.out.println("Stack Trace:");
			err.printStackTrace();
		}
	}

	public void start() throws Exception {
		byte[] orig = readFile(sorting_test);
		System.out.println(writeFileReplace(sorting_test, orig, bytes_private, bytes_public) + " replaces occurred.");
		ProcessBuilder pb = new ProcessBuilder("javac", "Execute.java", "Main.java", "-encoding", "utf-8");
		pb.directory(new File("."));
		pb.redirectError(new File("compile.err"));
		Process p = pb.start();
		int e_code;
		while (true) {
			try {
				e_code = p.exitValue();
				break;
			} catch (IllegalThreadStateException err) {
				// pass;
			}
		}
		writeFile(sorting_test, orig);
		if (e_code != 0) {
			System.out.println("Compile Error!");
			System.out.println("Error log was written in compile.err.");
			return;
		}
	}

	public byte[] readFile(String fn) throws FileNotFoundException, IOException {
		File f = new File(fn);
		byte[] orig = new byte[(int)f.length()];
		FileInputStream fis = new FileInputStream(f);
		fis.read(orig);
		fis.close();
		return orig;
	}

	public void writeFile(String fn, byte[] data) throws IOException {
		FileOutputStream fos = new FileOutputStream(fn);
		fos.write(data);
		fos.close();
	}

	public int writeFileReplace(String fn, byte[] data, byte[] orig, byte[] replace) throws IOException {
		int i, j, c = 0, len = data.length;
		FileOutputStream fos = new FileOutputStream(fn);
		for (i=0; i<len; ++i) {
			for (j=0; j<orig.length; ++j) {
				if (i+j >= len || data[i+j] != orig[j]) {
					break;
				}
			}
			if (j == orig.length) {
				fos.write(replace);
				i += orig.length - 1;
				++c;
			} else {
				fos.write(data[i]);
			}
		}
		fos.close();
		return c;
	}

}
