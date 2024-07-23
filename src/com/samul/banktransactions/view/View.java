package com.samul.banktransactions.view;

import java.util.List;

import com.samul.banktransactions.entity.Transaction;

public class View {

	public static void printTransactions(List<Transaction> bt) {
		for (Transaction elem : bt) {
			System.out.println(elem.toString());
		}
	}

}
