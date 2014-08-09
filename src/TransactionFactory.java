import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransactionFactory {
	private String[] inputList;
	private Output output;

	public TransactionFactory(String inputString) throws InvalidInputException {
		this.inputList = inputString.split(":");
		Transaction newTransaction = new Transaction(createOrder()); 
		this.output = newTransaction.processTransaction();
	}
	public String getOutputString(){
		return output.toString();
	}

	private Order createOrder() throws InvalidInputException {
		Countries orderCountries;
		Countries residentCountries;
		int ipodQuantity = 0;
		int iphoneQuantiy = 0;
		if (inputList.length == 6) {
			if (inputList[0].equalsIgnoreCase("Brazil")) {
				orderCountries = Countries.Brazil;
			} else if (inputList[0].equalsIgnoreCase("Argentina"))
				orderCountries = Countries.Argentina;
			else
				throw new InvalidInputException();
			String argentinaPassportRegEx = "^(A\\D{2}\\w{9})$";
			String brazilPassportRegEx = "^(B\\d{3}\\D{2}\\w{7})$";
			Pattern argentinaPattern = Pattern.compile(argentinaPassportRegEx);
			Pattern brazilPattern = Pattern.compile(brazilPassportRegEx);
			Matcher argentinaMatcher = argentinaPattern.matcher(inputList[1]);
			Matcher brazilMatcher = brazilPattern.matcher(inputList[1]);
			if(argentinaMatcher.find())
				residentCountries = Countries.Argentina;
			else if (brazilMatcher.find())
				residentCountries = Countries.Brazil;
			else
				throw new InvalidInputException();
			if(inputList[2].equalsIgnoreCase("IPHONE"))
				iphoneQuantiy = Integer.parseInt(inputList[3]);
			else if(inputList[2].equalsIgnoreCase("IPOD"))
				ipodQuantity = Integer.parseInt(inputList[3]);
			else
				throw new InvalidInputException();
			if(inputList[4].equalsIgnoreCase("IPHONE"))
				iphoneQuantiy = Integer.parseInt(inputList[5]);
			else if(inputList[4].equalsIgnoreCase("IPOD"))
				ipodQuantity = Integer.parseInt(inputList[5]);
			else
				throw new InvalidInputException();
		}
		else if(inputList.length == 5){
			if (inputList[0].equalsIgnoreCase("Brazil")) {
				orderCountries = Countries.Brazil;
			} else if (inputList[0].equalsIgnoreCase("Argentina"))
				orderCountries = Countries.Argentina;
			else
				throw new InvalidInputException();
			residentCountries = Countries.NotSpecified;
			if(inputList[1].equalsIgnoreCase("IPHONE"))
				iphoneQuantiy = Integer.parseInt(inputList[2]);
			else if(inputList[1].equalsIgnoreCase("IPOD"))
				ipodQuantity = Integer.parseInt(inputList[2]);
			else
				throw new InvalidInputException();
			if(inputList[3].equalsIgnoreCase("IPHONE"))
				iphoneQuantiy = Integer.parseInt(inputList[4]);
			else if(inputList[3].equalsIgnoreCase("IPOD"))
				ipodQuantity = Integer.parseInt(inputList[4]);
			else
				throw new InvalidInputException();
		}
		else
			throw new InvalidInputException();
		Order newOrder = new Order(orderCountries,ipodQuantity,iphoneQuantiy,residentCountries);
		return newOrder;
	}
}
