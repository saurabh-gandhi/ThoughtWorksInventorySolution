public abstract class Inventory implements Stock {

	protected IPod iPod;
	protected IPhone iPhone;
	protected Countries location;

	@Override
	public boolean isInStock(InventoryItem item) {
		if (item.getQuantity() > 0)
			return true;
		return false;
	}

	@Override
	public void sellStock(int noOfItemsOrdered, InventoryItem item){
			item.setQuantity(item.getQuantity() - noOfItemsOrdered);
	}

	@Override
	public double getGrossPrice(int noOfItemsOrdered, InventoryItem item) {
			sellStock(noOfItemsOrdered, item);
			return noOfItemsOrdered*item.getSellPrice();
}

	@Override
	public boolean isDelieveryPossible(int noOfItemsOrdered, InventoryItem item) {
		int itemsRemaining = item.getQuantity() - noOfItemsOrdered;
		if (itemsRemaining > 0)
			return true;
		return false;
	}

}
