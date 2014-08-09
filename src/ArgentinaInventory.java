public class ArgentinaInventory extends Inventory {

	public ArgentinaInventory() {
		super();
		this.iPhone = new IPhone(150, 50);
		this.iPod = new IPod(100, 100);
		this.location = Countries.Argentina;
	}
}
