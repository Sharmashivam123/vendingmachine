package vendingmachine.Exception;

public class ItemNotInStockException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotInStockException(String message) {
		super(message);
	}

}
