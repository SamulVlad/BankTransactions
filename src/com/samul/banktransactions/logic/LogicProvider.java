package com.samul.banktransactions.logic;

import com.samul.banktransactions.logic.impl.TransactionsLogicImpl;

public final class LogicProvider {
	private final static LogicProvider instance = new LogicProvider();
	
	private TransactionsLogic logic = new TransactionsLogicImpl(); 
	
	private LogicProvider () {
		
	}
	
	public static LogicProvider getInstance() {
		return instance;
	}
	
	public TransactionsLogic getBankTransactionsLogic() {
		return logic;
	}

}
