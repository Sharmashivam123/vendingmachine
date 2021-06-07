package vendingmachine.modal;

public enum Coin {

	PENNY(10),
	NICKLE(50),
	DIME(20);

	private int denomination;
	
	Coin(int i) {
		this.denomination = i;
	}
	
	public int getDenomination() {
		return denomination;
	}
}
