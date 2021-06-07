package vendingmachine.Exception;

public class CoinNotAcceptableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoinNotAcceptableException(String msg, long coin) {
		super(msg+" returned change "+coin);
	}
	
}
