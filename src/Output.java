public class Output {
	double sellPrice;
	String totalSalePrice;
	int ipodBrazilInventory;
	int iphoneBrazilInventory;
	int ipodArgentinaInventory;
	int iphoneArgentinaInventory;


	public Output() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return totalSalePrice + ":" + ipodBrazilInventory + ":"
				+ ipodArgentinaInventory + ":" + iphoneBrazilInventory + ":"
				+ iphoneArgentinaInventory;
	}

}
