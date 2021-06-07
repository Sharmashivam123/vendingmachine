package vendingmachine.app;

import java.util.List;

import vendingmachine.Exception.CoinNotAcceptableException;
import vendingmachine.Exception.ItemNotInStockException;
import vendingmachine.Exception.NotEnoughChangeException;
import vendingmachine.Exception.NotFullPaidException;
import vendingmachine.modal.Bucket;
import vendingmachine.modal.Coin;
import vendingmachine.modal.Item;

public interface VendingMachine {
	
	void displayInventory();
	
	Bucket<Item, List<Coin>> dispenseItemAndChange() throws NotEnoughChangeException, NotFullPaidException;
	
	List<Coin> refund() throws NotEnoughChangeException;
	
	void reset();
	
	void insertCoin(long coin) throws CoinNotAcceptableException;
	
	void refillCoinsAndItems();
	
	void collectItem(String item) throws ItemNotInStockException;
	
	void stats() ;
}
