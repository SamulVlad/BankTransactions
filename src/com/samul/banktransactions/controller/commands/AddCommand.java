package com.samul.banktransactions.controller.commands;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.samul.banktransactions.controller.Command;
import com.samul.banktransactions.entity.Operation;
import com.samul.banktransactions.entity.Transaction;
import com.samul.banktransactions.logic.LogicException;
import com.samul.banktransactions.logic.LogicProvider;
import com.samul.banktransactions.logic.TransactionsLogic;
import com.samul.banktransactions.view.View;

public class AddCommand implements Command{
	private TransactionsLogic logic = LogicProvider.getInstance().getBankTransactionsLogic();

	@Override
	public String execute(String request) {
		String response;
		String[] params = request.split("\n");
		
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("yyyy-MM-dd");

		try {
			Date date = format.parse(params[1].split("=")[1]);
			Operation operation = Operation.valueOf(params[2].split("=")[1]);
			String content = params[3].split("=")[1];
			double sum = Double.parseDouble(params[4].split("=")[1]);
			
			Transaction transaction = new Transaction(date, operation, content, sum);
			 
			logic.add(transaction);
			
			response = "Succsess. Transaction added.";
		} catch (ParseException e) {
			response = "Invalid parametr Date";
		} catch (NumberFormatException e) {
			response = "Invalid parametr Sum";
		} catch (LogicException e) {
			response = "Something went wrong";
		}

		return response;
	}

}
