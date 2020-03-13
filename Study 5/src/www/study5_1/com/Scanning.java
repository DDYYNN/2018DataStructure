package www.study5_1.com;

import java.util.Scanner;// 항상 java.util.Scanner를 import해야 콘솔 입력이 가능하다.

public class Scanning {
	public static void main(String[] args){
		String source = "1 5 7";
		Scanner sc = new Scanner(source);
		
		int num1 = sc.nextInt();
		int num2 = sc.nextInt();
		int num3 = sc.nextInt();
		
		int sum = num1 + num2 + num3;
		
		System.out.printf("문자열에 저장된 %d, %d, %d의 값의 합은 %d이다.", num1, num2, num3, sum);
		sc.close();
		
		
		
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("당신의 이름은?");
		String name = keyboard.nextLine();
		System.out.println("안녕하세요 " + name + "님.");
		
		System.out.println("당신은 스파게티를 좋아하는데, 진실입니까?");
		boolean isTrue = keyboard.nextBoolean();
		if(isTrue == true)
			System.out.println("오~ 좋아하는군요!");
		else
			System.out.println("이런 아니었군요.");
		
		System.out.println("당신과 동생의 키는 어떻게 되나요?");
		double height1 = keyboard.nextDouble();
		double height2 = keyboard.nextDouble();
		double diff = height1 - height2;
		if(diff > 0)
			System.out.printf("당신이 %.2f만큼 크군요.", diff);
		else if (diff == 0)
			System.out.println("키가 똑같군요!");
		else
			System.out.printf("당신이 %.2f만큼 작군요.", -diff);
		
		keyboard.close();
			
	}
}

