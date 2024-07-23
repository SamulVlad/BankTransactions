package com.samul.banktransactions.controller;

public class Controller {
	private final char separator = '\n';

	private final CommandProvider commandProvider = new CommandProvider();

	public String execute(String request) {
		String commandName;
		Command command;
		String response;

		commandName = request.substring(0, request.indexOf(separator));
		command = commandProvider.getCommand(commandName);
		response = command.execute(request);

		return response;

	}

}
