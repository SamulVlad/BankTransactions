package com.samul.banktransactions.controller.commands;

import java.util.List;

import com.samul.banktransactions.controller.Command;
import com.samul.banktransactions.entity.Transaction;
import com.samul.banktransactions.logic.*;
import com.samul.banktransactions.view.View;

public class GetAllTransactionsCommand implements Command {
	private TransactionsLogic logic = LogicProvider.getInstance().getBankTransactionsLogic();

	@Override
	public String execute(String request) {
		String response;

		try {
			List<Transaction> transactions = logic.getAll();
			response = transactions.toString();
		} catch (LogicException e) {
			response = "Something went wrong " + e;
		}

		return response;
	}

}
