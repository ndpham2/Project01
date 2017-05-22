import java.io.BufferedReader;
import java.io.FileReader;

public class Blackout {
	private static String equation;
	public static String parsedEquation = "";
	public static String blackedOutEquation;

	public static void main(String[] args) {
		String fileName = args[0];
		getEquation(fileName);

		System.out.println(fileName);
		System.out.println("Equation is: " + equation);

		blackoutNumbers(equation);

		/*
		 * if (parseExpression(blackedOutEquation)) {
		 * System.out.println("Equation " + parsedEquation + " is invalid."); }
		 * else { System.out.println("Equation " + parsedEquation +
		 * " is valid."); }
		 */

	}

	// this gets the equation from the text file
	public static void getEquation(String fileName) {
		try {
			FileReader inputFile = new FileReader(fileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);
			equation = bufferReader.readLine();
		} catch (Exception e) {

		}

	}

	// this black outs numbers
	public static String blackoutNumbers(String equation) {
		blackedOutEquation = null;

		for (int i = 0; i < equation.length(); i++) {
			char firstBlackoutCharacter = equation.charAt(i);
			for (int j = i + 1; j < equation.length(); j++) {
				char secondBlackoutCharacter = equation.charAt(j);

				System.out.println("Blackout pair: " + firstBlackoutCharacter + secondBlackoutCharacter);

				blackedOutEquation = equation.substring(0, i) + equation.substring(i + 1, j)
						+ equation.substring(j + 1, equation.length());
				System.out.println("Blacked Out Expression: " + blackedOutEquation);

				boolean result = isValidExpression(blackedOutEquation);
				System.out.println(result);

				if (result == true) {
					boolean result2 = parseExpression(blackedOutEquation);
					System.out.println(result2);

				}

			}

		}

		return blackedOutEquation;

	}

	private static boolean isValidExpression(String equation) {
		// ensure that there is one equal sign
		int count = 0;
		for (int i = 0; i < equation.length(); i++) {
			if (equation.charAt(i) == '=') {
				count++;
			}
		}
		if (count == 0 || count > 1) {
			return false;
		}
		// ensure that we do not have two or more consecutive operators
		// valid operators are: +-*/%
		for (int i = 0; i < equation.length() - 1; i++) {
			if (isOperator(equation.charAt(i)) && isOperator(equation.charAt(i + 1))) {
				return false;
			}
		}

		// ensures that equation does not start or end with an operator
		if (isOperator(equation.charAt(0)) || isOperator(equation.charAt(equation.length() - 1))) {
			return false;
		}

		return true;
	}

	private static boolean isOperator(char symbol) {
		if (symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '%' || symbol == '=') {
			return true;
		}

		return false;

	}

	private static boolean parseExpression(String equation) {
		String[] expression = equation.split("[*+-/%=]");
		System.out.println(expression);

		double answer1 = evaluate(expression[0]);
		double answer2 = evaluate(expression[1]);

		if (answer1 == answer2) {
			return true;
		} else {
			return false;
		}

	}

	private static int evaluate(String string) {
		for(int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			if (isOperator(character)) {
				
				// this keeps WHERE (index) first operator is located
				int firstOperatorIndex = i; 
				
				// first number goes from beginning until first operator
				int  firstNumber = Integer.parseInt(string.substring(0, firstOperatorIndex));
				System.out.println(firstNumber);
				System.out.println("I found operator at this" + i);
			}
			
			switch (operator) {
			case "+":
					return firstNumber + secondNumber;
			case "-":
					return firstNumber - secondNumber;
			case "/":
					return firstNumber / secondNumber;
			case "*":
					return firstNumber * secondNumber;
			case "%":
					return firstNumber % secondNumber;
			default:
					throw new RuntimeException("unkonwn operator: " + operator);
			}
		}

		return 0;
		// split on operators...
		// what's left are numbers. parse them to int 
		// then figure out how to combine operands
		// so figure out which operator was between operator one and two...
		// do the math for it
	}
	
	
}
