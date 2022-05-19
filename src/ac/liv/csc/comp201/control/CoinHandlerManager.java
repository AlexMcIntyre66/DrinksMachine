package ac.liv.csc.comp201.control;

import ac.liv.csc.comp201.model.IMachine;

public class CoinHandlerManager {
	
	public void handleCoinsIn(IMachine machine) {
		String code=machine.getCoinHandler().getCoinKeyCode();
		System.out.println("Code is "+code);
		if (code != null){
			int coinValue = Coin.getCoinFromCode(code).getCoinWorth();
			machine.setBalance(machine.getBalance()+coinValue);
		}
	}
	
	/**
	 * Pay's the current balance in coins if it can be paid, this is a best try function
	 * if not enough coins then it pays the best it can do
	 */
	public void returnChange(IMachine machine) {
		machine.getDisplay().setTextString("Trying to pay change back");
		Coin coinValues[] = Coin.getAllcoins();
		for(int i=0; i < coinValues.length; i++){
			if (coinValues[i].getCoinWorth() <= machine.getBalance()){
				if (machine.getCoinHandler().coinAvailable(coinValues[i].getCoinCode()) != false){				
					machine.getCoinHandler().dispenseCoin(coinValues[i].getCoinCode());
					machine.setBalance(machine.getBalance()-coinValues[i].getCoinWorth());
					i = 0;
				}
			}
		}
		System.out.println("PAYING CHANGE!!!! balance is "+machine.getBalance());
	}

}
