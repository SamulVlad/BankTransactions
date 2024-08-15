package com.samul.banktransactions.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.samul.banktransactions.dao.DaoException;
import com.samul.banktransactions.dao.TransactionsDAO;
import com.samul.banktransactions.entity.Operation;
import com.samul.banktransactions.entity.Transaction;

public class FileTransactionsDAO implements TransactionsDAO {

	private static final String separator = "\uFFFF";
	private static final String filePath = "resources\\transactions.txt";

	@Override
	public void saveTransaction(Transaction bt) throws DaoException {

		FileWriter out = null;
		BufferedWriter br = null;
		PrintWriter pw = null;

		try {
			out = new FileWriter(filePath, true);
			br = new BufferedWriter(out);
			pw = new PrintWriter(br);
			pw.println(transactionToFileString(bt));
		} catch (IOException e) {
			throw new DaoException(e);
		} finally {
			pw.close();
		}

	}

	@Override
	public List<Transaction> readAllTranactions() throws DaoException {
		List<Transaction> result = new ArrayList();

		FileReader fr = null;
		BufferedReader in = null;

		try {
			fr = new FileReader(filePath);
			in = new BufferedReader(fr);

			while (in.ready()) {
				String inString = in.readLine();
				Transaction bt = parseFileString(inString);
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

	private String transactionToFileString(Transaction bt) {
		StringBuilder result = new StringBuilder();

		result.append(bt.getId()).append(separator).append(bt.getDate().getTime()).append(separator)
				.append(bt.getOperation()).append(separator).append(bt.getContent()).append(separator)
				.append(bt.getSum()).append(separator);

		return result.toString();

	}

	private Transaction parseFileString(String fileString) {
		Transaction result;

		String[] parsed = fileString.split(separator);

		result = new Transaction(Long.parseLong(parsed[0]), new Date(Long.parseLong(parsed[1])),
				Operation.valueOf(parsed[2]), parsed[3], Double.valueOf(parsed[4]));

		return result;
	}

}
