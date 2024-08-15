package com.samul.banktransactions.dao.impl;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.samul.banktransactions.dao.DaoException;
import com.samul.banktransactions.dao.TransactionsDAO;
import com.samul.banktransactions.entity.Transaction;

public class SerializedObjectFileTransactionsDAO implements TransactionsDAO {

	private static final String filePath = "resources\\serialized_transactions.txt";

	@Override
	public void saveTransaction(Transaction bt) throws DaoException {
		// не просто append в файл новые объекты, а читаем уже записанные, добавлям к ним новый объект,
		// уже тогда всю пачку записываем в пустой файл. 
		//т.к. если append, то ObjectOutputStream каждый раз при создании в начало потока добавлеет header и потом при чтении всех объектов по порядку ObjectInputStream не может их корректно прочитать

		
		List<Transaction> allTransactions = new ArrayList();

		File file = new File(filePath);
		if (file.exists() && file.length() != 0) {
			allTransactions = readAllTranactions();
		}

		allTransactions.add(bt);

		FileOutputStream out = null;
		ObjectOutputStream oos = null;

		try {
			out = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(out);

			for (Transaction trans : allTransactions) {
				oos.writeObject(trans);
			}
		} catch (IOException e) {
			throw new DaoException(e);
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					throw new DaoException(e);
				}
			}
		}
	}

	@Override
	public List<Transaction> readAllTranactions() throws DaoException {
		List<Transaction> result = new ArrayList();

		FileInputStream fis = null;
		ObjectInputStream in = null;

		try {
			fis = new FileInputStream(filePath);
			in = new ObjectInputStream(fis);

			while (fis.available() > 0) {
				Transaction bt;
				try {
					bt = (Transaction) in.readObject();
				} catch (ClassNotFoundException e) {
					throw new DaoException(e);
				}
				result.add(bt);
			}
		} catch (FileNotFoundException e) {
			throw new DaoException(e);
		} catch (IOException e) {
			throw new DaoException(e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new DaoException(e);
				}
			}
		}

		return result;
	}

}
