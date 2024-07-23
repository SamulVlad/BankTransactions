package com.samul.banktransactions.controller.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.samul.banktransactions.controller.Command;
import com.samul.banktransactions.entity.Transaction;
import com.samul.banktransactions.logic.*;
import com.samul.banktransactions.view.View;

public class FindOnPeriodCommand implements Command{
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

			List<Transaction> transactions = logic.find(dateFrom, dateTo);

			response = transactions.toString();
		} catch (ParseException e) {
			response = "Invalid parametr Date";
		} catch (LogicException e) {
			response = "Something went wrong";
		}
		
		return response;
	}

}
