package com.samul.banktransactions.controller.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.samul.banktransactions.controller.Command;
import com.samul.banktransactions.entity.Transaction;
import com.samul.banktransactions.logic.LogicException;
import com.samul.banktransactions.logic.LogicProvider;
import com.samul.banktransactions.logic.TransactionsLogic;

public class GetDebitCommand implements Command {
	private TransactionsLogic logic = LogicProvider.getInstance().getBankTransactionsLogic();

	@Override
	public String execute(String request) {
		String response;
		String[] params = request.split("\n");

		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("yyyy-MM-dd");

		Date dateFrom, dateTo;
		try {
			dateFrom = format.parse(params[1].split("=")[1]);
			dateTo = format.parse(params[2].split("=")[1]);

			response = Double.toString(logic.getDebit(dateFrom, dateTo));
		} catch (ParseException e) {
			e.printStackTrace();
			response = "Invalid parametr Date";
		} catch (LogicException e) {
			response = "Something went wrong";
		}

		return response;
	}

}
