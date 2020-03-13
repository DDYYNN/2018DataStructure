import java.io.*;

public class Subway {
	public static void main(String args[]) throws IOException {
		File file = new File(args[0]);
		BufferedReader br;
		Graph g = new Graph();
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		while (true) {
			String bf = br.readLine();
			if (bf.equals(""))
				break;
			String[] bufs = bf.split(" ");
			g.addStation(bufs[0], bufs[1], bufs[2]);
		}
		while (true) {
			String bf = br.readLine();
			if (bf == null)
				break;
			String[] bufs = bf.split(" ");
			g.addEdge(bufs[0], bufs[1], Integer.parseInt(bufs[2]));
		}

		br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input, g);

			} catch (Exception e) {
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input, Graph g) {
		String args[] = input.split(" ");
		if (args.length > 2) {
			g.findMinTransfer(args[0], args[1]);
		} else {
			g.findShortest(args[0], args[1]);
		}
	}
}
