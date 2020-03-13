package www.packagetest.com;

// 다른 package에서 Triangle class를 불러오려면
// 1. Triangle class는 public 선언되어야 한다.
// 		1.1. Triangle class가 포함된 .java는 이름이 public class name과 같아야 한다.
// 2. import packagename.*; 가 선언되어야 한다.

import www.Q7_1_1.com.*;

public class Hi {
	public static void main(String[] args){
		Triangle tr1 = new Triangle(12,24);			
		System.out.println(tr1);
	}
}
