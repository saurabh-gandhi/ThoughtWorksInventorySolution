public class Order {
	private Countries orderCountry;
	private int noOfIpods;
	private int noOfIphones;
	private Countries residentCountries;
	public Order(){
		super();
	}

	public Order(Countries orderCountry, int noOfIpods, int noOfIphones,
			Countries residentCountries) {
		super();
		this.orderCountry = orderCountry;
		this.noOfIpods = noOfIpods;
		this.noOfIphones = noOfIphones;
		this.residentCountries = residentCountries;
	}

	public Countries getOrderCountry() {
		return orderCountry;
	}

	public void setOrderCountry(Countries orderCountry) {
		this.orderCountry = orderCountry;
	}

	public int getNoOfIpods() {
		return noOfIpods;
	}

	public void setNoOfIpods(int noOfIpods) {
		this.noOfIpods = noOfIpods;
	}

	public int getNoOfIphones() {
		return noOfIphones;
	}

	public void setNoOfIphones(int noOfIphones) {
		this.noOfIphones = noOfIphones;
	}

	public Countries getResidentCountries() {
		return residentCountries;
	}

	public void setResidentCountries(Countries residentCountries) {
		this.residentCountries = residentCountries;
	}

}
