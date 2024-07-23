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
import com.samul.banktransactions.view.View;

public class FindOnDateCommand implements Command{
	private TransactionsLogic logic = LogicProvider.getInstance().getBankTransactionsLogic();

	@Override
	public String execute(String request) {
		String response;
		String[] params = request.split("\n");
		
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("yyyy-MM-dd");
		
		Date date;
		try {
			date = format.parse(params[1].split("=")[1]);
			List<Transaction> transactions = logic.find(date);
			response = transactions.toString();
		} catch (ParseException e) {
			response = "Invalid parametr Date";
		} catch (LogicException e) {
			response = "Something went wrong";
		}
		
		return response;
		
	}

}
