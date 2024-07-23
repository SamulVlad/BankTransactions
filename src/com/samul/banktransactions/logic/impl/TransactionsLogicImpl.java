package com.samul.banktransactions.logic.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.samul.banktransactions.dao.DaoException;
import com.samul.banktransactions.dao.DaoProvider;
import com.samul.banktransactions.dao.TransactionsDAO;
import com.samul.banktransactions.entity.Operation;
import com.samul.banktransactions.entity.Transaction;
import com.samul.banktransactions.logic.LogicException;
import com.samul.banktransactions.logic.TransactionsLogic;

public class TransactionsLogicImpl implements TransactionsLogic {
	final DaoProvider provider = DaoProvider.getInstance();
	final TransactionsDAO dao = provider.getDao();

	public void add(Transaction bt) throws LogicException {
		try {
			dao.saveTransaction(bt);
		} catch (DaoException e) {
			throw new LogicException(e);
		}
	}

	public List<Transaction> getAll() throws LogicException {
		List<Transaction> result = new ArrayList<>();
		try {
			result = dao.readAllTranactions();
		} catch (DaoException e) {
			throw new LogicException(e);
		}
		return result;
	}

	public List<Transaction> find(String param)  throws LogicException {
		List<Transaction> result = new ArrayList<>();

		List<Transaction> allTransactions = getAll();

		for (Transaction elem : allTransactions) {
			if (elem.getContent().contains(param)) {
				result.add(elem);
			}
		}

		return result;
	}

	public List<Transaction> find(Date param) throws LogicException {
		List<Transaction> result = new ArrayList<>();
		List<Transaction> allTransactions = getAll();

		LocalDate localDateParam = param.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		for (Transaction elem : allTransactions) {
			LocalDate localDateElem = elem.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (localDateElem.equals(localDateParam)) {
				result.add(elem);
			}
		}
		return result;
	}

	public List<Transaction> find(Date from, Date to) throws LogicException{
		List<Transaction> result = new ArrayList<>();
		List<Transaction> allTransactions = getAll();

		LocalDate localDateFrom = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().minusDays(1);
		LocalDate localDateTo = to.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().plusDays(1);

		for (Transaction elem : allTransactions) {
			LocalDate localDateElem = elem.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			if (localDateElem.isAfter(localDateFrom) && localDateElem.isBefore(localDateTo)) {
				result.add(elem);
			}
		}
		return result;
	}

	@Override
	public double getDebit(Date from, Date to) throws LogicException {
		double result = 0;
		List<Transaction> transactions = find(from, to);

		for (Transaction elem : transactions) {
			if (elem.getOperation() == Operation.DEBIT) {
				result += elem.getSum();
			}
		}
		return result;
	}

	@Override
	public double getCredit(Date from, Date to) throws LogicException {
		double result = 0;
		List<Transaction> transactions = find(from, to);

		for (Transaction elem : transactions) {
			if (elem.getOperation() == Operation.CREDIT) {
				result += elem.getSum();
			}
		}
		return result;
	}

}
