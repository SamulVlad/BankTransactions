package com.samul.banktransactions.dao;

import java.util.List;

import com.samul.banktransactions.entity.Transaction;

public interface TransactionsDAO {

	void saveTransaction(Transaction bt) throws DaoException;
	
	List<Transaction> readAllTranactions() throws DaoException;

}
