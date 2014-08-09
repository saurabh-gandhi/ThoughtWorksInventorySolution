public abstract class InventoryItem {
	private String inventoryItemName;
	private double sellPrice;
	private int quantity;
	public InventoryItem(String inventoryItemName, double sellPrice,
			int quantity) {
		super();
		this.inventoryItemName = inventoryItemName;
		this.sellPrice = sellPrice;
		this.quantity = quantity;
	}
	public String getInventoryItemName() {
		return inventoryItemName;
	}
	public void setInventoryItemName(String inventoryItemName) {
		this.inventoryItemName = inventoryItemName;
	}
	public double getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
