public class Affine {
	

	public static void main(String[] args) {
		int a = Integer.parseInt(args[0]);
		int b = Integer.parseInt(args[1]);
		String encryptedText = args[2];
		
		char[] characterArray = {' ', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
				'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', '!'};
		
		int[] encryptedNumbers = new int[encryptedText.length()];
		
		int[] decryptedNumbers = new int[encryptedNumbers.length];
		
		// for loop that changes encryptedText into encryptedNumbers
		for(int i = 0; i < encryptedText.length(); i++) {
			char character = encryptedText.charAt(i);
			for(int j = 0; j < characterArray.length; j++) {
				if (character == characterArray[j]) {
					encryptedNumbers[i] = j;
				}
			}
		}
		
		int inverseOfA = 0;
		int n = 29;
		int inverseOfAFound = 0;
		while (inverseOfA <= n) {
			if ((a * inverseOfA) % n == 1) {
				inverseOfAFound = inverseOfA;
			}
			inverseOfA++;
			
		}
		
		// takes encryptedNumbers and decodes it into decryptedNumbers
		for(int k = 0; k < encryptedNumbers.length; k++) {	
			int calculation = ((inverseOfAFound) * (encryptedNumbers[k] - b)) % 29;
			if (calculation < 0) {
				calculation += 29;
			}
			decryptedNumbers[k] = calculation;
		}
		
		// displays message
		String message = 
				"a 	= " + a + "\n" +
				"b 	= " + b + "\n" +
				"a^-1 	= " + inverseOfA + "\n" +
				"Encrypted Message: " + encryptedText + "\n" +
				"Decrypted Message: ";
 		System.out.print(message);
 		
 		// takes decryptedNumbers and turns it into a decrypted word
		for(int m = 0; m < decryptedNumbers.length; m++) {
			char letter = characterArray[decryptedNumbers[m]];
				System.out.print(letter);
					}
		
		
		
		}
		
	}
