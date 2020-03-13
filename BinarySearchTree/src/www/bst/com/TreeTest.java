package www.bst.com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreeTest {
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BinarySearchTree<String> bst = new BinarySearchTree<>();

		while (true)
		{
			try
			{
				System.out.println("\nDecide : 1. Insert  2. Delete  3. Print  4. Reset Tree  5. Insert Sentence  6. Print in a sentence  7. Insert Charseq");
				String input = br.readLine();
				if (input.compareTo("QUIT") == 0)
					break;
				switch(input){
				case "1" : 
					System.out.println("Insert Key : ");
					bst.insert(br.readLine());
					break;
				case "2" :
					System.out.println("Delete Key : ");
					try{
						bst.delete(br.readLine());
					} catch(Exception e){
						System.err.println("Item NOT FOUND");
					}
					break;
				case "3" :
					bst.printTree(bst.getRoot(), 0, 0);
					break;
				case "4" :
					bst.deleteAll();
					break;
				case "5" :
					System.out.println("Insert Sentence : ");
					String key_args[] = br.readLine().split(" ");
					for(int i = 0; i < key_args.length; i++){
						bst.insert(key_args[i].toLowerCase());
					}
					break;
				case "6" :
					bst.printInOrder(bst.getRoot());
					break;
				case "7" :
					System.out.println("Insert Sequence : ");
					String key_args1[] = br.readLine().replaceAll(" ", "").split("");
					for(int i = 0; i < key_args1.length; i++){
						bst.insert(key_args1[i]);
					}
					break;
				default :
					System.err.println("Wrong choice.");
				}
			}
			catch (IOException e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}
}

