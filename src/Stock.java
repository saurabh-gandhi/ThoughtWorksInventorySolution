public interface Stock {
	public boolean isInStock(InventoryItem item);

	public void sellStock(int noOfItemsOrdered,InventoryItem item);
	
	public double getGrossPrice(int noOfItemsOrdered, InventoryItem item);
	
	public boolean isDelieveryPossible(int noOfItemsOrdered, InventoryItem item);

}
