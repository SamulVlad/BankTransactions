package com.samul.banktransactions.controller;

import java.util.HashMap;
import java.util.Map;

import com.samul.banktransactions.controller.commands.*;

public class CommandProvider {
	private final Map<CommandName, Command> commandsMap = new HashMap();

	public CommandProvider() {
		commandsMap.put(CommandName.ADD, new AddCommand());
		commandsMap.put(CommandName.GET_ALL, new GetAllTransactionsCommand());
		commandsMap.put(CommandName.FIND_ON_DATE, new FindOnDateCommand());
		commandsMap.put(CommandName.FIND_ON_PERIOD, new FindOnPeriodCommand());
		commandsMap.put(CommandName.FIND_BY_CONTENT, new FindByContentCommand());
		commandsMap.put(CommandName.GET_DEBIT, new GetDebitCommand());
		commandsMap.put(CommandName.GET_CREDIT, new GetCreditCommand());
	}

	public Command getCommand(String commandName) {
		Command command;
		try {
			command = commandsMap.get(CommandName.valueOf(commandName.toUpperCase()));

		} catch (IllegalArgumentException | NullPointerException e) {
			command = new NoSuchCommand();
		}

		return command;

	}

}
