package www.test.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern; 

class BigIntegerTest {
	
	public static final Pattern EXPRESSION_PATTERN = Pattern.compile("\\s*[+|-]?\\s*\\d{1,100}\\s*");
    private static final int MAX_DIGITS = 200;
    private static final int START_COMPARE = 1; 	// used for isAbsBiggerThan
    private char[] intChar;
    private int sign;
	
    private int getLength(){				// Length of a BigInteger
    	int cnt = 0;
    	for(char e : intChar){
    		if(e != 0) cnt++;
    	}
    	return cnt;
    }
    
    private int charToInt(char c){			// Converts char digits into int	digits
    	if(c == 0) return 0;
    	else return c-'0';
    }
    
    private int isAbsBiggerThan(BigIntegerTest big, int start){ // Compares absolute values of two BigIntegers.
    	// returns 1 if mine is bigger; -1 if other is bigger; 0 if both are identical.
    	// start is a parameter used for recursion.
    	
    	if(start > getLength()) return 0;
    	if(getLength() > big.getLength())
    		return 1;
    	else if(getLength() < big.getLength())
    		return -1;
    	else{
    		char myMaxDig = intChar[getLength()-start];
    		char otherMaxDig = big.intChar[big.getLength()-start];
    		if(myMaxDig != otherMaxDig){
    			return (myMaxDig > otherMaxDig ? 1 : -1);
    		}
    		else return isAbsBiggerThan(big, start+1);
    	}
    }
    
    private BigIntegerTest inverseSign(){	// Inverses sign of a BigInteger and make it into new Instance.
    	// For the purpose of conserving signs of parameters while executing other methods
    	BigIntegerTest inv = new BigIntegerTest(0);
    	inv.sign = -sign;
    	for(int i = 0; i < MAX_DIGITS; i++){
    		inv.intChar[i] = intChar[i];
    	}
    	return inv;
    }
  
    private String trim(String s){		// Trims white spaces and useless zeros in a String.
    	s = s.replaceAll("\\s","");
    	if(s.length() == 1 || s.charAt(0) != '0') return s;
    	else return trim(s.substring(1));
    }

    private static String arrToString(int[] num1){
    	String result = "";
    	for(int i = 0; i < num1.length - 1; i++){
			num1[i+1] += num1[i]/10;
			num1[i] %= 10;
			result = (char)(num1[i] + '0') + result;
		}
    	return result;
    }
    
    public BigIntegerTest(int i)
    {
    	this(String.valueOf(i));			
    }
    
	public BigIntegerTest(String s)
    {
    	s = trim(s);					// Trim all useless zeros
    	
    	// input sign if exists
    	char first = s.charAt(0);
    	if (first == '-'){
    		sign = -1;
    		s = s.substring(1);
    	}
    	else{
    		sign = 1;
    		if(first == '+') s = s.substring(1);
    	}
    	if (s.equals("0")) sign = 0;
    	
    	// input digits backward
    	intChar = new char[MAX_DIGITS];
    	int len = s.length();
    	for(int i = 0; i < len; i++){
    		intChar[i] = s.charAt(len-i-1);
    	}
    }
	
	public BigIntegerTest(int[] num1) {
		this(arrToString(num1));
	}
	
	public BigIntegerTest add(BigIntegerTest big){
		if(sign >= 0 && big.sign >= 0){
			// if both are positive, do addition (with carry)
			int maxlen = getLength() > big.getLength() ? getLength() : big.getLength();
			String s = "";
			int carry = 0;
			for(int i = 0; i < maxlen; i++){
				int sumUp = charToInt(intChar[i]) + charToInt(big.intChar[i]) + carry;
				s = (char)('0'+sumUp%10) + s;
				carry = sumUp/10; 
			}
			if(carry > 0) s = '1' + s;
			return new BigIntegerTest(s);
		}
		
		else if(sign < 0 && big.sign < 0){
			// if a,b are both negative, a+b = -((-a)+(-b))
			return ((this.inverseSign()).add(big.inverseSign())).inverseSign();
		}
		
		else{
			// if ab < 0, a+b = a-(-b)
			return subtract(big.inverseSign());
		}
		
	}
	
	public BigIntegerTest subtract(BigIntegerTest big)
    {	
		if(sign * big.sign >= 0){
			if(isAbsBiggerThan(big,START_COMPARE) < 0){
				// if ab >= 0 and |a|<|b|, a-b = -(b-a)
				return (big.subtract(this)).inverseSign();
			}
				// if ab >= 0 and |a|>|b|, do a-b (with carry)
			int maxlen = getLength();
			String s = "";
			int carry = 0;
			for(int i = 0; i < maxlen; i++){
				int takeOff = charToInt(intChar[i]) - charToInt(big.intChar[i]) + carry + 10;	// add 10 to make takeOff positive.
				s = (char)('0'+takeOff%10) + s;
				carry = takeOff/10 - 1; 
			}
			BigIntegerTest result = new BigIntegerTest(s);
			result.sign = sign;
			return result;
		}
		
		else{
			// if ab < 0, a-b = a+(-b)
			return add(big.inverseSign());
		}
    }
	
	public BigIntegerTest multiply(BigIntegerTest big)
	{
		int[] num1 = new int[MAX_DIGITS];
		for(int i = 0; i < getLength()+big.getLength()-1; i++){
			num1[i] = 0;
			for(int j = 0; j < big.getLength(); j++){
				if(i-j < 0 || i-j >= getLength()) continue;
				num1[i] += charToInt(intChar[i-j]) * charToInt(big.intChar[j]);
			}
		}
		BigIntegerTest result = new BigIntegerTest(num1);
		result.sign = sign * big.sign;
		return result;
	}
	
	
	@Override
	public String toString(){				// This is called automatically whenever System.out.println is used.
		String result = "";
    	int i = 0;
		while(intChar[i] != 0){
			result = intChar[i++] + result;
		}
		if(sign < 0) result = '-' + result;
		return result;
	}
}

public class Test{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		Matcher m = BigIntegerTest.EXPRESSION_PATTERN.matcher(input);
		while(m.find()) System.out.println(m.group());
	}
}



