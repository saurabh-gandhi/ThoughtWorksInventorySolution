public class Transaction {
	Order order;
	BrazilInventory brazilInventory;
	ArgentinaInventory argentinaInventory;
	int totalIpods;
	int totalIphones;

	public Transaction(Order order) {
		this.order = order;
		brazilInventory = new BrazilInventory();
		argentinaInventory = new ArgentinaInventory();
		totalIphones = argentinaInventory.iPhone.getQuantity()
				+ brazilInventory.iPhone.getQuantity();
		totalIpods = argentinaInventory.iPod.getQuantity()
				+ brazilInventory.iPod.getQuantity();
	}

	public Output processTransaction() {
		Output output = new Output();
		if (order.getNoOfIpods() > totalIpods
				|| order.getNoOfIphones() > totalIphones) {
			output.totalSalePrice = "OUT_OF_STOCK";
		} else {
			output.totalSalePrice = Integer.toString((int) (calculateMinimumCostOfIpod()
							+ calculateMinimumCostOfIphone()));
		}
		output.iphoneArgentinaInventory = argentinaInventory.iPhone
				.getQuantity();
		output.ipodArgentinaInventory = argentinaInventory.iPod.getQuantity();
		output.iphoneBrazilInventory = brazilInventory.iPhone.getQuantity();
		output.ipodBrazilInventory = brazilInventory.iPod.getQuantity();

		return output;

	}

	private double calculateMinimumCostOfIpod() {
		double salePriceOfIpod, transportationCostOfIpod;
		int itemsToBeShippedFromArgentina = 0, itemsToBeShippedFromBrazil = 0;
		if (order.getOrderCountry().equals(Countries.Brazil)
				&& order.getResidentCountries().equals(Countries.Brazil)) {
			if (order.getNoOfIpods() <= brazilInventory.iPod.getQuantity()) {
				itemsToBeShippedFromBrazil = order.getNoOfIpods();
				itemsToBeShippedFromArgentina = 0;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = 0.0;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPod.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIpods()
						- itemsToBeShippedFromBrazil;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = Math
						.ceil((float) itemsToBeShippedFromArgentina / 10) * 400;
			}
		} else if (order.getOrderCountry().equals(Countries.Argentina)
				&& (order.getResidentCountries().equals(Countries.Argentina) || order
						.getResidentCountries().equals(Countries.NotSpecified))) {
			if (order.getNoOfIpods() <= argentinaInventory.iPod.getQuantity()) {
				itemsToBeShippedFromArgentina = order.getNoOfIpods();
				itemsToBeShippedFromBrazil = 0;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = 0.0;
			} else {
				itemsToBeShippedFromArgentina = argentinaInventory.iPod
						.getQuantity();
				itemsToBeShippedFromBrazil = order.getNoOfIpods()
						- itemsToBeShippedFromBrazil;

				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = Math
						.ceil((float) itemsToBeShippedFromBrazil / 10) * 400;
			}
		} else if (order.getOrderCountry().equals(Countries.Argentina)
				&& order.getResidentCountries().equals(Countries.Brazil)) {
			if (order.getNoOfIpods() <= 9) {
				itemsToBeShippedFromArgentina = order.getNoOfIpods();
				itemsToBeShippedFromBrazil = 0;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = 0.0;
			} else if (order.getNoOfIpods() > 9
					&& order.getNoOfIpods() <= brazilInventory.iPod
							.getQuantity()) {
				itemsToBeShippedFromArgentina = order.getNoOfIpods() % 10;
				itemsToBeShippedFromBrazil = order.getNoOfIpods()
						- itemsToBeShippedFromArgentina;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = Math
						.ceil((float) itemsToBeShippedFromBrazil / 10) * 320;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPod.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIpods()
						- itemsToBeShippedFromBrazil;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = Math
						.ceil((float) itemsToBeShippedFromBrazil / 10) * 320;
			}

		} else if (order.getOrderCountry().equals(Countries.Brazil)
				&& order.getResidentCountries().equals(Countries.NotSpecified)) {
			if (order.getNoOfIpods() <= brazilInventory.iPod.getQuantity()) {
				itemsToBeShippedFromArgentina = 0;
				itemsToBeShippedFromBrazil = order.getNoOfIpods();
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = 0.0;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPod.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIpods()
						- itemsToBeShippedFromBrazil;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = Math
						.ceil((float) itemsToBeShippedFromArgentina / 10) * 400;
			}
		} else {
			if (order.getNoOfIpods() <= brazilInventory.iPod.getQuantity()) {
				itemsToBeShippedFromArgentina = 0;
				itemsToBeShippedFromBrazil = order.getNoOfIpods();
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = 0.0;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPod.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIpods()
						- itemsToBeShippedFromBrazil;
				salePriceOfIpod = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPod)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPod);
				transportationCostOfIpod = Math
						.ceil((float) itemsToBeShippedFromArgentina / 10) * 320;
			}
		}

		return salePriceOfIpod + transportationCostOfIpod;
	}

	private double calculateMinimumCostOfIphone() {
		double salePriceOfIphone, transportationCostOfIphone;
		int itemsToBeShippedFromBrazil, itemsToBeShippedFromArgentina;
		if (order.getOrderCountry().equals(Countries.Brazil)
				&& order.getResidentCountries().equals(Countries.Brazil)) {
			if (order.getNoOfIphones() <= brazilInventory.iPhone.getQuantity()) {
				itemsToBeShippedFromBrazil = order.getNoOfIphones();
				itemsToBeShippedFromArgentina = 0;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone);
				transportationCostOfIphone = 0.0;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPhone
						.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIphones()
						- itemsToBeShippedFromBrazil;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math
						.ceil((float) itemsToBeShippedFromArgentina / 10) * 400;
			}
		} else if (order.getOrderCountry().equals(Countries.Argentina)
				&& (order.getResidentCountries().equals(Countries.Argentina) || order
						.getResidentCountries().equals(Countries.NotSpecified))) {
			if (order.getNoOfIphones() <= 8) {
				itemsToBeShippedFromBrazil = 0;
				itemsToBeShippedFromArgentina = order.getNoOfIphones();
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = 0.0;
			} else if (order.getNoOfIphones() > 8
					&& order.getNoOfIphones() <= brazilInventory.iPhone
							.getQuantity()) {
				itemsToBeShippedFromArgentina = (order.getNoOfIphones() % 10 <= 8) ? order
						.getNoOfIphones() % 10 : 8;
				itemsToBeShippedFromBrazil = order.getNoOfIphones()
						- itemsToBeShippedFromArgentina;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math
						.ceil((float) itemsToBeShippedFromBrazil / 10) * 400;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPhone
						.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIphones()
						- itemsToBeShippedFromBrazil;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math
						.ceil((float) itemsToBeShippedFromBrazil / 10) * 400;
			}
		} else if (order.getOrderCountry().equals(Countries.Argentina)
				&& order.getResidentCountries().equals(Countries.Brazil)) {
			if (order.getNoOfIphones() <= 6) {
				itemsToBeShippedFromBrazil = 0;
				itemsToBeShippedFromArgentina = order.getNoOfIphones();
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = 0.0;
			} else if (order.getNoOfIphones() > 6
					&& order.getNoOfIphones() <= brazilInventory.iPhone
							.getQuantity()) {
				itemsToBeShippedFromArgentina = (order.getNoOfIphones() % 10 <= 6) ? order
						.getNoOfIphones() % 10 : 6;
				itemsToBeShippedFromBrazil = order.getNoOfIphones()
						- itemsToBeShippedFromArgentina;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math
						.ceil((float) itemsToBeShippedFromBrazil / 10) * 320;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPhone
						.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIphones()
						- itemsToBeShippedFromBrazil;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math
						.ceil((float) itemsToBeShippedFromBrazil / 10) * 320;
			}
		} else if (order.getOrderCountry().equals(Countries.Brazil)
				&& order.getResidentCountries().equals(Countries.Argentina)) {
			if (order.getNoOfIphones() <= brazilInventory.iPhone.getQuantity()) {
				itemsToBeShippedFromArgentina = 0;
				itemsToBeShippedFromBrazil = order.getNoOfIphones();
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math
						.ceil((float) itemsToBeShippedFromArgentina / 10) * 320;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPhone
						.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIphones()
						- itemsToBeShippedFromBrazil;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math.ceil((float) itemsToBeShippedFromArgentina / 10) * 320;
			}
		} else// if(order.getOrderCountry().equals(Countries.Brazil)
				// &&
				// order.getResidentCountries().equals(Countries.NotSpecified))
		{
			if (order.getNoOfIphones() <= brazilInventory.iPhone.getQuantity()) {
				itemsToBeShippedFromArgentina = 0;
				itemsToBeShippedFromBrazil = order.getNoOfIphones();
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = Math
						.ceil((float) itemsToBeShippedFromArgentina / 10) * 400;
			} else {
				itemsToBeShippedFromBrazil = brazilInventory.iPhone
						.getQuantity();
				itemsToBeShippedFromArgentina = order.getNoOfIphones()
						- itemsToBeShippedFromBrazil;
				salePriceOfIphone = brazilInventory.getGrossPrice(
						itemsToBeShippedFromBrazil, brazilInventory.iPhone)
						+ argentinaInventory.getGrossPrice(
								itemsToBeShippedFromArgentina,
								argentinaInventory.iPhone);
				transportationCostOfIphone = ((float) itemsToBeShippedFromArgentina / 10) * 320;
			}
		}

		return salePriceOfIphone + transportationCostOfIphone;
	}

}
