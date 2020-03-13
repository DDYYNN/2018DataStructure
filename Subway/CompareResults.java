import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompareResults {
	public static void main(String[] args) throws IOException{
		File mine = new File(args[0] + ".txt");
		File yours = new File(args[1] + ".txt");
		BufferedReader br_mine = new BufferedReader(new InputStreamReader(new FileInputStream(mine)));
		BufferedReader br_yours = new BufferedReader(new InputStreamReader(new FileInputStream(yours)));

		int i = 1; int j = 0; int k = 0;
		while(true){
			String m1 = br_mine.readLine();
			String m2 = br_mine.readLine();
			String y1 = br_yours.readLine();
			String y2 = br_yours.readLine();
			if(m1 == null || y1 == null || m2 == null || y2 == null) break;
			if(!(m1.equals(y1) && m2.equals(y2))){
				if(i / 2 == 1){
					System.out.println("WRONG Fastest Route on " + i + "th test.");
					System.out.println("<My output> : \n" + m1 + "\n" + m2);
					System.out.println("<Exact output> : \n" + y1 + "\n" + y2);
					j++;
				}
				else{
					System.out.println("WRONG MinTransfer Route on " + i + "th test.");
					System.out.println("<My output> : \n" + m1 + "\n" + m2);
					System.out.println("<Exact output> : \n" + y1 + "\n" + y2);
					k++;
				}
			}
			i++;
			br_mine.readLine();
			br_yours.readLine();
		}
		System.out.println("Score : Out of " + i + "tests,");
		System.out.println(j + " wrong SHORTEST & " + k + " wrong MINTRANSFER");
	}
}

