package www.biginteger.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BigInteger {
	public static final String QUIT_COMMAND = "quit";
	public static final String MSG_INVALID_INPUT = "입력이 잘못되었습니다.";

	// implement this
	public static final Pattern EXPRESSION_PATTERN = Pattern.compile("\\s*[+-]?\\s*\\d{1,100}\\s*");
	private static final int MAX_DIGITS = 200;
	private static final int START_COMPARE = 1; // used for isAbsBiggerThan

	private char[] intChar;
	private int sign;

	private int getLength() { // Length of a BigInteger
		int cnt = 0;
		for (char e : intChar) {
			if (e != 0)
				cnt++;
		}
		return cnt;
	}

	private int charToInt(char c) { // Converts char digits into int digits
		if (c == 0)
			return 0;
		else
			return c - '0';
	}

	private int isAbsBiggerThan(BigInteger big, int start) { // Compares
																// absolute
																// values of two
																// BigIntegers.
		// returns 1 if mine is bigger; -1 if other is bigger; 0 if both are
		// identical.
		// start is a parameter used for recursion.

		if (start > getLength())
			return 0;
		if (getLength() > big.getLength())
			return 1;
		else if (getLength() < big.getLength())
			return -1;
		else {
			char myMaxDig = intChar[getLength() - start];
			char otherMaxDig = big.intChar[big.getLength() - start];
			if (myMaxDig != otherMaxDig) {
				return (myMaxDig > otherMaxDig ? 1 : -1);
			} else
				return isAbsBiggerThan(big, start + 1);
		}
	}

	private BigInteger inverseSign() { // Inverses sign of a BigInteger and make
										// it into a new Instance.
		// For the purpose of conserving signs of parameters while executing
		// other methods
		BigInteger inv = new BigInteger(0);
		inv.sign = -sign;
		for (int i = 0; i < MAX_DIGITS; i++) {
			inv.intChar[i] = intChar[i];
		}
		return inv;
	}

	private String trimZeros(String s) { // Trims useless zeros in a String.
		if (s.length() == 1 || s.charAt(0) != '0')
			return s;
		else
			return trimZeros(s.substring(1));
	}

	private static String arrToString(int[] num1) {
		String result = "";
		for (int i = 0; i < num1.length - 1; i++) {
			num1[i + 1] += num1[i] / 10;
			num1[i] %= 10;
			result = (char) (num1[i] + '0') + result;
		}
		return result;
	}

	public BigInteger(int i) {
		this(String.valueOf(i));
	}

	public BigInteger(String s) {
		s = s.replaceAll("\\s", ""); // Trim all white spaces
		s = trimZeros(s); // Trim all useless zeros

		// input sign if exists
		char first = s.charAt(0);
		if (first == '-') {
			sign = -1;
			s = s.substring(1);
		} else {
			sign = 1;
			if (first == '+')
				s = s.substring(1);
		}
		if (s.equals("0"))
			sign = 0;

		// input digits backward
		intChar = new char[MAX_DIGITS];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			intChar[i] = s.charAt(len - i - 1);
		}
	}

	public BigInteger(int[] num1) {
		this(arrToString(num1));
	}

	public BigInteger add(BigInteger big) {
		if (sign >= 0 && big.sign >= 0) {
			// if both are positive, do addition (with carry)
			int maxlen = getLength() > big.getLength() ? getLength() : big.getLength();
			String s = "";
			int carry = 0;
			for (int i = 0; i < maxlen; i++) {
				int sumUp = charToInt(intChar[i]) + charToInt(big.intChar[i]) + carry;
				s = (char) ('0' + sumUp % 10) + s;
				carry = sumUp / 10;
			}
			if (carry > 0)
				s = '1' + s;
			return new BigInteger(s);
		}

		else if (sign < 0 && big.sign < 0) {
			// if a,b are both negative, a+b = -((-a)+(-b))
			return ((this.inverseSign()).add(big.inverseSign())).inverseSign();
		}

		else {
			// if ab < 0, a+b = a-(-b)
			return subtract(big.inverseSign());
		}

	}

	public BigInteger subtract(BigInteger big) {
		if (sign * big.sign >= 0) {
			if (isAbsBiggerThan(big, START_COMPARE) < 0) {
				// if ab >= 0 and |a|<|b|, a-b = -(b-a)
				return (big.subtract(this)).inverseSign();
			}
			// if ab >= 0 and |a|>|b|, do a-b (with carry)
			int maxlen = getLength();
			String s = "";
			int carry = 0;
			for (int i = 0; i < maxlen; i++) {
				int takeOff = charToInt(intChar[i]) - charToInt(big.intChar[i]) + carry + 10; // add
																								// 10
																								// to
																								// make
																								// takeOff
																								// positive.
				s = (char) ('0' + takeOff % 10) + s;
				carry = takeOff / 10 - 1;
			}
			BigInteger result = new BigInteger(s);
			if (result.sign != 0)
				result.sign = sign;
			return result;
		}

		else {
			// if ab < 0, a-b = a+(-b)
			return add(big.inverseSign());
		}
	}

	public BigInteger multiply(BigInteger big) {
		int[] num1 = new int[MAX_DIGITS];
		for (int i = 0; i < getLength() + big.getLength() - 1; i++) {
			num1[i] = 0;
			for (int j = 0; j < big.getLength(); j++) {
				if (i - j < 0 || i - j >= getLength())
					continue;
				num1[i] += charToInt(intChar[i - j]) * charToInt(big.intChar[j]);
			}
		}
		BigInteger result = new BigInteger(num1);
		result.sign = sign * big.sign;
		return result;
	}

	@Override
	public String toString() { // This is called automatically whenever
								// System.out.println is used.
		String result = "";
		int i = 0;
		while (intChar[i] != 0) {
			result = intChar[i++] + result;
		}
		if (sign < 0)
			result = '-' + result;
		return result;
	}

	static BigInteger evaluate(String input) throws IllegalArgumentException, StringIndexOutOfBoundsException {
		Matcher m = EXPRESSION_PATTERN.matcher(input);
		String left = "", right = "";
		int indx = 0;
		if (m.find()) {
			left = m.group();
			indx += left.length();
		}

		char opr = input.charAt(indx++);

		if (m.find(indx)) {
			right = m.group();
		}

		switch (opr) {
		case '+':
			return (new BigInteger(left)).add(new BigInteger(right));
		case '-':
			return (new BigInteger(left)).subtract(new BigInteger(right));
		case '*':
			return (new BigInteger(left)).multiply(new BigInteger(right));
		default:
			throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args) throws Exception {
		try (InputStreamReader isr = new InputStreamReader(System.in)) {
			try (BufferedReader reader = new BufferedReader(isr)) {
				boolean done = false;
				while (!done) {
					String input = reader.readLine();

					try {
						done = processInput(input);
					} catch (IllegalArgumentException e) {
						System.err.println(MSG_INVALID_INPUT);
					} catch (StringIndexOutOfBoundsException e) {
						System.err.println(MSG_INVALID_INPUT);
					}
				}
			}
		}
	}

	static boolean processInput(String input) throws IllegalArgumentException {
		boolean quit = isQuitCmd(input);

		if (quit) {
			return true;
		} else {
			BigInteger result = evaluate(input);
			System.out.println(result);

			return false;
		}
	}

	static boolean isQuitCmd(String input) {
		return input.equalsIgnoreCase(QUIT_COMMAND);
	}
}
