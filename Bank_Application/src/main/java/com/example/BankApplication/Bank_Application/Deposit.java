package com.example.BankApplication.Bank_Application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Deposit {
	
	public static void parseDeposit(JSONObject dep) {
//		System.out.println(dep);
		JSONObject obj = (JSONObject) dep.get("deposit_amount");
		String deposit_amount = (String) obj.get("value");
		String currency_code = (String) obj.get("currency_code");
		
		String status = (String) dep.get("deposit_status");
		
		String account_no = (String) dep.get("account_no");
		
		JSONObject obj1 = (JSONObject) dep.get("available_balance");
		
		String total_amount = String.valueOf(Double.parseDouble((String) obj1.get("value")) + Double.parseDouble(deposit_amount));
		
		String event_code = (String) dep.get("deposit_event_code");
		
		String code = "T" + event_code.substring(1);
		
		/*
		 * System.out.println(deposit_amount + " " + currency_code + " " + status + " "
		 * + currency_code + " " + account_no + " " + total_amount);
		 */		
		
		Transaction transaction = new Transaction(account_no, code, status, total_amount, currency_code);
		transaction.transaction_details();	
	}
	
	@SuppressWarnings("unchecked")
	public void deposit() {
		JSONParser jsonParser = new JSONParser();
	
		try {
			FileReader reader = new FileReader("/home/kirito/Temporary/deposit_stream.json");
			try {
				Object obj = jsonParser.parse(reader);
//				System.out.println(obj);
				JSONObject obj1 = (JSONObject) obj;
				Object obj2 = obj1.get("deposit_info");
				
				JSONArray deposit_list = (JSONArray) obj2;
				
				deposit_list.forEach(dep -> parseDeposit((JSONObject) dep));
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
