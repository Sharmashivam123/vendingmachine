package vendingmachine.modal;

public enum Item {

	COKE("Coke", 20),
	SNACK("Snack", 10),
	CHCOCOLATES("Desert", 10);

	private String value;
	private long rate;
	
	Item(String value, long rate) {
		this.value = value;
		this.rate = rate;
	}

	public String getValue() {
		return value;
	}

	public long getRate() {
		return rate;
	} 

}
