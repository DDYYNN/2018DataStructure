package www.linkedlist.com;

import java.util.Scanner;

public class ListMain {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		ListReferenceBased Data = new ListReferenceBased();
		boolean quit = false;
		while(!quit){
			System.out.println("[" + Data.size()+" items stored.]");
			delay(100);
			showMenu();
			int input = 0;
			try { 
				input = sc.nextInt();
			}
			catch (Exception e) {}
			sc.nextLine();
			switch(input){
			case 1:
				System.out.println("Insert Data...");
				System.out.print("Item : ");
				Object newItem = sc.nextLine();
				System.out.print("Index : ");
				int index = sc.nextInt();
				sc.nextLine();
				try {
					Data.add(index, newItem);
					System.out.println("Complete!\n");
				} catch (InvalidIndexException e) {
					System.err.println("!! "+ e.getMessage());
				}
				break;
			case 2:
				System.out.println("Delete Data...");
				System.out.print("Index : ");
				int index1 = sc.nextInt();
				sc.nextLine();
				try {
					Data.remove(index1);
					System.out.println("Complete!\n");
				} catch (InvalidIndexException e) {
					System.err.println("!! " + e.getMessage());
				}
				break;
			case 3:
				Data.printList();
				break;
			case 4:
				Data.removeAll();
				System.out.println("Complete!\n");
				break;
			case 5:
				quit = true;
				System.out.println("Bye!");
				break;
			default:
				System.err.println("invalid input!");
			}
			delay(100);
		}
		sc.close();
	}
	
	public static void showMenu(){
		System.out.println("What do you want to do?");
		System.out.println("1.Insert 2.Delete 3.Show All Data 4.Remove All Data 5. Quit");
		System.out.print(">> ");
	}
	
	public static void delay(int time){
		try {
			Thread.sleep(time);
		}
		catch (Exception e) { System.err.println("Delay error!\ncause:"+ e); }
	}
	
}
