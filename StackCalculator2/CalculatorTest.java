import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class CalculatorTest
{
	public static void main(String args[])
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true)
		{
			try
			{
				String input = br.readLine();
				if (input.compareTo("q") == 0)
					break;

				command(input);
			}
			catch (Exception e)
			{
				System.out.println("입력이 잘못되었습니다. 오류 : " + e.toString());
			}
		}
	}

	private static void command(String input)
	{
		try {
			String postfix = toPostfix(input);
			long result = postfixEval(postfix);
			// All errors occur above.
			System.out.println(postfix);
			System.out.println(result);
		} catch (Exception e) {
			// e.getMessage() always returns "ERROR".
			System.out.println(e.getMessage());
		}
	}
	
	// Here are defined the expected patterns of operators and operands.
	// OPERATOR is defined for the sake of StringTokenizer's delimitation.
	public static final String OPERATOR = "+-*/%^()";
	public static final String OPERATOR_PATTERN = "\\+|\\-|\\*|\\/|\\%|\\~|\\^|\\(|\\)";
	public static final String OPERAND_PATTERN = "\\d+";
	public static final String ERR_MSG = "ERROR";
	
	private static String toPostfix(String input) throws Exception{
		/*
		 * Parse infix expression input(String input)
		 * and convert it into postfix expression
		 * without any parenthesis.
		 */
		
		// Filter out input with blank parentheses.
		if(input.contains("()")) throw new Exception(ERR_MSG);
		
		// Tokenizing the infix input by means of OPERATOR's delimitation.
		StringTokenizer inputTkns = new StringTokenizer(input, OPERATOR, true);
		// Stack for operators
		Stack<String> oprStack = new Stack<String>();
		// Converted postfix expression
		String postfix = "";
		
		/* 
		 * << Determining Unary "-" >>
		 * "-" is unary in 2 conditions:
		 *		Unary-1. The very initial.
		 *		Unary-2. Right before is a operator excluding "(".
		 * and if not in such conditions "-" is binary (denoted as Binary-).
		 * isUnaryCond is true if one of those conditions above holds;
		 * else it is false.
		 */
		
		// Unary-1. The very initial.
		boolean isUnaryCond = true;
		
		while(inputTkns.hasMoreTokens()){
			/* 
			 * tkn is a trimmed token of the infix input, and can be
			 * 		1. "(spaces)" (whitespaces btw operators)
			 *		2. "(spaces)operand(spaces)"
			 *		3. An operator (single-character)
			 *		4. "(spaces)digits with spaces inside(spaces)"
			 * and (spaces) are trimmed by tkn.trim().
			 * Case 4 is expected to be an exception,
			 * since it does not match OPERAND_PATTERN (or OPERATOR_PATTERN).
			 */
			String tkn = inputTkns.nextToken().trim();
			
			// Case 1 (tkn is blank) : do nothing
			if(tkn.length() == 0) continue;
			
			// Case 2 (tkn is an operand) : write tkn on postfix expression
			if(tkn.matches(OPERAND_PATTERN)){
				if(tkn.length() > 1 && tkn.startsWith("0"))
					throw new Exception(ERR_MSG);
				postfix += (tkn + " ");
				
				// Binary- : following "-" is binary.
				isUnaryCond = false;
			}
			
			// Case 3 (tkn is an operator)
			else if(tkn.matches(OPERATOR_PATTERN)){
				
				 /*
				  * If tkn is ")" : 
				  * then pop all operators before "("
				  * and append them to postfix expression in popped order.
				  * 
				  * Throw exception
				  * when ")" does not meet "("
				  * or nothing is between parentheses.
				  * (EmptyStackException occurs.)
				 */
				if(tkn.equals(")")){
					try{
						while(!oprStack.peek().equals("(")){
							postfix += (oprStack.pop() + " ");
						}
						oprStack.pop();
						
						// Binary- : according to Unary-2, "-" after ")" is binary.
						isUnaryCond = false;
					}
					catch(Exception e){
						throw new Exception(ERR_MSG);
					}
				}
				
				/* If tkn is not ")" : 
				 * 1. Convert "-" to "~" if unary condition holds.
				 * 2. Consider following cases:
				 * 		2.1. If tkn has less precedence than top of oprStack:
				 *			- (A) pop all operators in stack until you meet
				 *				   "(" or item with less precedence than tkn.
				 *		2.2. IF tkn has same precedence as top of oprStack:
				 *			- ignore the cases when tkn is right-associative("^","~")
				 *			- do (A)
				 *		2.3. Else (tkn has higher precedence than top)
				 *			- (just go to 3)
				 * 3. Push tkn to oprStack.
				*/ 
				else{
					// 1. Convert unary "-"
					if(tkn.equals("-") && isUnaryCond)
						tkn = "~";
					
					// 2. If needed, pop all operaters (2.1~2.2)
					int p_tkn = precedence(tkn);
					if(!oprStack.empty() && p_tkn > 0){
						// precenence(opr) < 0 means opr is a parenthesis.
						String peek = oprStack.peek();
						int p_peek = precedence(peek);
						if(p_peek > 0){
							if((p_tkn < p_peek) ||
									 (p_tkn == p_peek && p_tkn != 3 && p_tkn != 4)){
								while(!(oprStack.empty() || oprStack.peek().equals("(") ||
										p_tkn > precedence(oprStack.peek()))){
									postfix += (oprStack.pop() + " ");
								}
							}
						}
					}
					
					// 3. Push tkn
					oprStack.push(tkn);
					
					// Unary-2.
					isUnaryCond = true;
				}
			}
			
			// Other cases including Case 4. tkn is neither operand nor operator
			// Parsing error. Input is not a correct infix expression.
			else
				throw new Exception(ERR_MSG);
		}
		
		// After processing all tokens,
		// pop all items inside oprStack and append them to postfix expression.
		while(!oprStack.empty()){
			String tmp = oprStack.pop();
			if(tmp.matches("\\(|\\)"))
				throw new Exception(ERR_MSG);
			postfix += (tmp + " ");
		}
		// Parsing finished.
		// Delete the last space and return
		return postfix.trim();
	}
	
	private static int precedence(String opr) throws Exception {
		/*
		 * Determine precedence of an operator(String opr).
		 * Larger value means the operator has higher precedence.
		 * Precedence of parentheses is negative.
		 */
		switch(opr){
		case "(": case ")":
			return -1;
		case "+": case "-":
			return 1;
		case "*": case "/": case "%":
			return 2;
		case "~":
			return 3;
		case "^":
			return 4;
		default:
			// this is not expected to be executed
			throw new Exception(ERR_MSG);
		}
	}
	
	private static long postfixEval(String postfix) throws Exception {
		/*
		 * Read and evaluate input postfix expression(String postfix)
		 * within the "long" range.
		 */
		
		// Tokenizing the postfix input
		StringTokenizer exprTkns = new StringTokenizer(postfix, " ", false);
		// Stack operating "long" values
		// Long class wraps a value of the primitive type long in an object.
		Stack<Long> stack = new Stack<Long>();
		
		while(exprTkns.hasMoreTokens()){
			String tkn = exprTkns.nextToken();
			
			// If tkn is an operand,
			// push into the stack.
			if(tkn.matches(OPERAND_PATTERN)){
				stack.push(Long.parseLong(tkn));
			}
			
			// If tkn is an operator,
			// pop required operand(s) and push evaluated result.
			// Throw error when required operand does not exist,
			// or the expression is incalculable.
			else if(tkn.matches(OPERATOR_PATTERN)){
				long result = 0;
				
				// Unary operator("~")
				if(tkn.equals("~")){
					if(stack.empty()) throw new Exception(ERR_MSG);
					long opnd = stack.pop();
					result = -opnd;
				}
				// Binary operators
				else{
					if(stack.empty()) throw new Exception(ERR_MSG);
					long opnd2 = stack.pop();
					if(stack.empty()) throw new Exception(ERR_MSG);
					long opnd1 = stack.pop();
					switch(tkn){
					case "+":
						result = opnd1 + opnd2;
						break;
					case "-":
						result = opnd1 - opnd2;
						break;
					case "*":
						result = opnd1 * opnd2;
						break;
					case "/":
						// catch / by zero error
						if(opnd2 == 0) throw new Exception(ERR_MSG);
						result = opnd1 / opnd2;
						break;
					case "%":
						// catch / by zero error
						if(opnd2 == 0) throw new Exception(ERR_MSG);
						result = opnd1 % opnd2;
						break;
					case "^":
						// catch 0^(negative) error
						if(opnd1 == 0 && opnd2 < 0) throw new Exception(ERR_MSG);
						result = (long) Math.pow(opnd1, opnd2);
						break;
					default:
						throw new Exception(ERR_MSG);
					}
				}
				stack.push(result);
			}
			else
				throw new Exception(ERR_MSG);
		}
		// Now, the stack must have only 1 item.
		long answer = stack.pop();
		if(!stack.empty())
			throw new Exception(ERR_MSG);
		
		return answer;
	}
}