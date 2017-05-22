import java.io.BufferedReader;
import java.io.FileReader;

public class Blackout {

	private static String equation;
	public static String parsedEquation = "";
	public static String blackedOutEquation;

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
					System.out.println("======================================================");
					System.out.println("Final Result: " + result2);
					System.out.println("======================================================");

				}

			}

		}

		return blackedOutEquation;

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

	public static void main(String[] args) {
		String fileName = args[0];
		getEquation(fileName);

		System.out.println(fileName);
		System.out.println("Equation is: " + equation);

		blackoutNumbers(equation);

	}

	private static double evaluate(String string) {

		String[] expressionNumbers = string.split("[*+-/%]");
		double total = 0;
		int operatorsFoundCounter = 0;

		for (int i = 0; i < string.length(); i++) {
			char character = string.charAt(i);
			// If the character is an operator, then we want to do a calculation
			if (isOperator(character)) {

				int firstNumber = Integer.parseInt(expressionNumbers[operatorsFoundCounter]);
				int secondNumber = Integer.parseInt(expressionNumbers[operatorsFoundCounter + 1]);

				System.out.println("Operator: " + character);
				System.out.println("First Number for operation: " + firstNumber);
				System.out.println("Second Number for operation: " + secondNumber);

				operatorsFoundCounter++;

				int result = 0;
				switch (character) {
				case '+':
					result += firstNumber + secondNumber;
					break;
				case '-':
					result += firstNumber - secondNumber;
					break;
				case '/':
					result += firstNumber / secondNumber;
					break;
				case '*':
					result += firstNumber * secondNumber;
					break;
				case '%':
					result += firstNumber % secondNumber;
					break;
				default:
					throw new RuntimeException("unkonwn operator: " + character);
				}

				System.out.println("Operation Result: " + result);
				expressionNumbers[operatorsFoundCounter] = Integer.toString(result);
				// add the result of this operation to the running total
				total += result;
			}
		}

		// If you never find any operators, then the string must be the result
		if (operatorsFoundCounter == 0) {
			total = Integer.parseInt(string);
		}

		return total;

	}

	private static boolean isOperator(char symbol) {
		if (symbol == '+' || symbol == '-' || symbol == '*' || symbol == '/' || symbol == '%' || symbol == '=') {
			return true;
		}
		return false;
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
		for (int i = 0; i < equation.length() - 1; i++) {
			if (isOperator(equation.charAt(i)) && isOperator(equation.charAt(i + 1))) {
				return false;
			}
		}

		// ensure that equation does not start or end with an operator
		if (isOperator(equation.charAt(0)) || isOperator(equation.charAt(equation.length() - 1))) {
			return false;
		}

		return true;
	}

	private static boolean parseExpression(String equation) {
		String[] expression = equation.split("=");

		double answer1 = evaluate(expression[0]);
		double answer2 = evaluate(expression[1]);

		if (answer1 == answer2) {
			return true;
		} else {
			return false;
		}

	}

}
