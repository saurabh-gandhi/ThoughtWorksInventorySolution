
public class BrazilInventory extends Inventory {
	public BrazilInventory() {
		super();
		this.iPhone = new IPhone(100,100);
		this.iPod = new IPod(100,65);
		this.location = Countries.Brazil;
	}
}
