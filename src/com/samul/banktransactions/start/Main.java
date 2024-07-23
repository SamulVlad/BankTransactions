package com.samul.banktransactions.start;

import java.util.Date;
import java.util.List;

import com.samul.banktransactions.controller.Controller;
import com.samul.banktransactions.dao.DaoProvider;
import com.samul.banktransactions.dao.TransactionsDAO;
import com.samul.banktransactions.entity.Operation;
import com.samul.banktransactions.entity.Transaction;
import com.samul.banktransactions.logic.LogicProvider;
import com.samul.banktransactions.logic.TransactionsLogic;
import com.samul.banktransactions.view.View;

public class Main {

	public static void main(String[] args) {
		Controller contr = new Controller();
		
		System.out.println(contr.execute("ADD\ndate=2024-07-23\nOperation=DEBIT\nContent=Оплата в Green\nSum=55.42"));
		
		System.out.println(contr.execute("GET_ALL\n"));
		System.out.println(contr.execute("FIND_ON_DATE\ndate=2024-07-15"));
		System.out.println(contr.execute("FIND_ON_PERIOD\ndateFrom=2024-07-15\ndateTo=2024-07-20"));
		System.out.println(contr.execute("FIND_BY_CONTENT\nContent=Зар"));
		System.out.println(contr.execute("GET_DEBIT\ndateFrom=2024-07-15\ndateTo=2024-07-20"));
		System.out.println(contr.execute("GET_CREDIT\ndateFrom=2024-07-15\ndateTo=2024-07-20"));

	}

}
