package vendingmachine.app;

public class VendingMachineFactory {

	public static VendingMachine getVendingMachine() {
		return new VendingMachineImpl();
	}
	
}
