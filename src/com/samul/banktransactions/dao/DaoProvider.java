package com.samul.banktransactions.dao;

import com.samul.banktransactions.dao.impl.*;

public final class DaoProvider {
	private static final DaoProvider INSTANCE = new DaoProvider();
//	private TransactionsDAO dao = new FileTransactionsDAO();
	private TransactionsDAO dao = new SerializedObjectFileTransactionsDAO();

	private DaoProvider() {
	};

	public TransactionsDAO getDao() {
		return dao;
	}

	public static DaoProvider getInstance() {
		return INSTANCE;
	}

}
