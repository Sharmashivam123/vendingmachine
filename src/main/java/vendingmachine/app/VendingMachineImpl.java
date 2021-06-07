package vendingmachine.app;

import java.util.ArrayList;
import java.util.List;

import vendingmachine.Exception.CoinNotAcceptableException;
import vendingmachine.Exception.ItemNotInStockException;
import vendingmachine.Exception.NotEnoughChangeException;
import vendingmachine.Exception.NotFullPaidException;
import vendingmachine.inventory.Inventory;
import vendingmachine.modal.Bucket;
import vendingmachine.modal.Coin;
import vendingmachine.modal.Item;

public final class VendingMachineImpl implements VendingMachine {

	private long totalSales;
	private Inventory<Item> items;
	private Inventory<Coin> coins;
	private long currentBalance;
	private Item currentItem;

	public VendingMachineImpl() {
		items = new Inventory<>();
		coins = new Inventory<>();
		initialise();
	}

	private void initialise() {
		for (Item item : Item.values()) {
			items.put(item, 5);
		}
		for (Coin coin : Coin.values()) {
			coins.put(coin, 5);
		}
	}

	@Override
	public void displayInventory() {
		System.out.println("ITEM	|" + "	Price");
		System.out.println("-----------------------");
		items.getAllInventory().forEach(item -> System.out.println(item.getValue() + "		" + item.getRate()));
	}

	@Override
	public void collectItem(String item) throws ItemNotInStockException {
		for (Item it : Item.values()) {
			if (item.equalsIgnoreCase(it.getValue()) && items.hasItem(it)) {
				this.currentItem = it;
				return;
			}
		}
		this.reset();
		throw new ItemNotInStockException("Item Sold Out.");
	}

	@Override
	public void insertCoin(long coin) throws CoinNotAcceptableException {
		this.isCoinAcceptable(coin);
		this.currentBalance = coin;
	}

	private void isCoinAcceptable(long coin) throws CoinNotAcceptableException {
		for (Coin c : Coin.values()) {
			if (coin == c.getDenomination()) {
				return;
			}
		}
		this.reset();
		throw new CoinNotAcceptableException("Insert a right a coin ", coin);
	}

	@Override
	public Bucket<Item, List<Coin>> dispenseItemAndChange() throws NotEnoughChangeException, NotFullPaidException {
		isItemDispensable();
		long remainingBalance = this.currentBalance - currentItem.getRate();
		List<Coin> change = getChange(remainingBalance);
		Bucket<Item, List<Coin>> bucket = new Bucket<>();
		bucket.setK(change);
		bucket.setT(currentItem);
		totalSales += currentItem.getRate();
		return bucket;
	}

	private void isItemDispensable() throws NotFullPaidException {
		long remainingBalance = this.currentBalance - currentItem.getRate();
		if (remainingBalance < 0) {
			this.reset();
			throw new NotFullPaidException("Cash Not Available to Return" + currentBalance);
		}
	}

	private List<Coin> getChange(long amount) throws NotEnoughChangeException {

		List<Coin> change = new ArrayList<>();
		long balance = amount;
		while (amount > 0) {
			if (amount >= Coin.DIME.getDenomination()) {
				amount -= Coin.DIME.getDenomination();
				change.add(Coin.DIME);
			} else if (amount >= Coin.NICKLE.getDenomination()) {
				amount -= Coin.NICKLE.getDenomination();
				change.add(Coin.NICKLE);
			} else if (amount >= Coin.PENNY.getDenomination()) {
				amount -= Coin.PENNY.getDenomination();
				change.add(Coin.PENNY);
			} else {
				throw new NotEnoughChangeException("Machine Donot have change " + balance);
			}
		}
		return change;
	}

	@Override
	public List<Coin> refund() throws NotEnoughChangeException {
		List<Coin> refund = getChange(currentBalance);
		reset();
		for(Coin c: refund) {
			coins.deduct(c);
		}
		items.deduct(currentItem);
		return refund;
	}

	@Override
	public void reset() {
		this.currentBalance = 0;
		this.currentItem = null;
	}

	@Override
	public void refillCoinsAndItems() {
		this.initialise();
	}

	@Override
	public void stats() {
		System.out.println("Total Sales "+totalSales);
		System.out.println("Inventory Left "+items);
		System.out.println("Coins left "+coins);
	}

	
}
