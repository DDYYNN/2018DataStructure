import java.io.*;

public class Matching
{
	public static void main(String args[])
	{
		HashTable DB = new HashTable();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{	
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;

				command(input, DB);
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			} catch (Exception e) {
				System.out.println("잘못된 명령입니다. 오류 : " + e.toString());
				e.printStackTrace();
			}
		}
	}

	private static void command(String input, HashTable DB) throws Exception
	{
		DBProcessor pr = new DBProcessor(DB);			// DBProcessor가 공통적으로 처리한다.
		if (input.startsWith("< ")) {
			DB = pr.readFile(input.substring(2));		// read and parse the File
		} else if (input.startsWith("@ ")) {
			pr.printSlotStrings(input.substring(2));	// print all substring in the slot
		} else if (input.startsWith("? ")) {
			pr.searchPattern(input.substring(2));		// find all indexes that matches the pattern
		} else{	
			throw new Exception(input);
		}
	}
}
