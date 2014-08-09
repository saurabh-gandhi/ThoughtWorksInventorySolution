import java.util.Scanner;

public class Input {
	public static void main(String args[]) throws InvalidInputException {
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("INPUT");
		String inputString = inputScanner.nextLine(); 
		TransactionFactory transactionFactory = new TransactionFactory(inputString);
		System.out.println(transactionFactory.getOutputString());
	}
}
