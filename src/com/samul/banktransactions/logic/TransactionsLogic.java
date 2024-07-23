package com.samul.banktransactions.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.samul.banktransactions.entity.Transaction;

public interface TransactionsLogic {
	
	public void add(Transaction bt) throws LogicException;

	public List<Transaction> find(String param) throws LogicException;
	
	public List<Transaction> find(Date param) throws LogicException;
	
	public List<Transaction> find(Date from, Date to) throws LogicException;

	public double getDebit(Date from, Date to) throws LogicException;

	public double getCredit(Date from, Date to) throws LogicException;
	
	public List<Transaction> getAll()  throws LogicException;
}
