package com.samul.banktransactions.controller.commands;

import com.samul.banktransactions.controller.Command;
import com.samul.banktransactions.logic.LogicProvider;
import com.samul.banktransactions.logic.TransactionsLogic;

public class NoSuchCommand implements Command{

	@Override
	public String execute(String request) {
		return "No such command.";
	}

}
