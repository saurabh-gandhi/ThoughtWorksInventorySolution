import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TransactionFactoryTest {

	@Test
	public void TransactionTestOne() throws InvalidInputException {
		String expectedOutput = "2650:90:100:80:50";
		String actualOutput;
		TransactionFactory transactionFactory = new TransactionFactory(
				"BRAZIL:B123AB1234567:IPHONE:20:IPOD:10");
		actualOutput = transactionFactory.getOutputString();
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void TransactionTestTwo() throws InvalidInputException {
		String expectedOutput = "3910:90:100:80:48";
		String actualOutput;
		TransactionFactory transactionFactory = new TransactionFactory(
				"ARGENTINA:B123AB1234567:IPHONE:22:IPOD:10");
		actualOutput = transactionFactory.getOutputString();
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void TransactionTestThree() throws InvalidInputException {
		String expectedOutput = "19260:30:100:0:25";
		String actualOutput;
		TransactionFactory transactionFactory = new TransactionFactory(
				"BRAZIL:AAB123456789:IPHONE:125:IPOD:70");
		actualOutput = transactionFactory.getOutputString();
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void TransactionTestFour() throws InvalidInputException {
		String expectedOutput = "8550:100:50:80:45";
		String actualOutput;
		TransactionFactory transactionFactory = new TransactionFactory(
				"ARGENTINA:AAB123456789:IPOD:50:IPHONE:25");
		actualOutput = transactionFactory.getOutputString();
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void TransactionTestFive() throws InvalidInputException {
		String expectedOutput = "18500:0:50:50:50";
		String actualOutput;
		TransactionFactory transactionFactory = new TransactionFactory(
				"BRAZIL:IPHONE:50:IPOD:150");
		actualOutput = transactionFactory.getOutputString();
		assertEquals(expectedOutput, actualOutput);
	}

	@Test
	public void TransactionTestSix() throws InvalidInputException {
		String expectedOutput = "OUT_OF_STOCK:100:100:100:50";
		String actualOutput;
		TransactionFactory transactionFactory = new TransactionFactory(
				"BRAZIL:IPHONE:250:IPOD:150");
		actualOutput = transactionFactory.getOutputString();
		assertEquals(expectedOutput, actualOutput);
	}

}
