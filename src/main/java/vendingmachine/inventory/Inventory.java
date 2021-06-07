package vendingmachine.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory<T> {

	private Map<T, Integer> inventory;
	
	public Inventory() {
		inventory = new HashMap<>();
	}
	
	public void put(T item, int count) {
		inventory.put(item, count);
	}
	
	public void deduct(T item) {
		if(hasItem(item)) {
			Integer val = inventory.get(item);
			inventory.put(item, val-1);			
		}
	}

	public boolean hasItem(T item) {
		Integer val = inventory.get(item);
		return val > 0 ? true : false;
	}
	
	public List<T> getAllInventory(){
		return new ArrayList<>(inventory.keySet());
	}
}
