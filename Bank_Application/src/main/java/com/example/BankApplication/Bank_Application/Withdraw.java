package com.example.BankApplication.Bank_Application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Withdraw {
	
	public void parseWithdraw(JSONObject withdraw) {
		JSONObject obj = (JSONObject) withdraw.get("withdraw_amount");
				
		String withdraw_amount = (String) obj.get("value");
		String currency_code = (String) obj.get("currency_code");
		String withdraw_status = (String) withdraw.get("withdraw_status");
		
		JSONObject obj1 = (JSONObject) withdraw.get("available_balance");
		
		String balance = String.valueOf((Double.parseDouble((String) obj1.get("value")) - Double.parseDouble(withdraw_amount)));
		
		String account_no = (String) withdraw.get("account_no");
		String event_code = (String) withdraw.get("withdraw_event_code");
		String code = "T" + event_code.substring(1);
		
		/*
		 * System.out.println(withdrawl_amount + " " + currency_code + " " +
		 * withdraw_status + " " + balance + " " + account_no);
		 * 
		 */
		
		Transaction transaction = new Transaction(account_no, code, withdraw_status, balance, currency_code);
		transaction.transaction_details();
	}
	
	@SuppressWarnings("unchecked")
	public void withdraw() {
		JSONParser jsonParser = new JSONParser();
		
		try {
			FileReader file = new FileReader("/home/kirito/Temporary/withdraw_stream.json");
			try {
				JSONObject obj = (JSONObject) jsonParser.parse(file);
//				System.out.println(obj);
				JSONArray obj1 = (JSONArray) obj.get("withdraw_info");
//				System.out.println(obj1);
				obj1.forEach(withdraw -> parseWithdraw((JSONObject) withdraw));
				
				
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
