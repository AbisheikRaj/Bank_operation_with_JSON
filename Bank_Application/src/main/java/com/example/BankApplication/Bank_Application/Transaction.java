package com.example.BankApplication.Bank_Application;

import java.time.LocalDateTime;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Transaction {
	
	private String account_no;
	private String trans_id;
	private String trans_event_code;
	private LocalDateTime trans_initial_date;
	private LocalDateTime trans_update_date;
	private String trans_status;
	private String balance;
	private String currency_code;
	
	public JSONArray info;
	public JSONObject deposit_details;
	
	public Transaction(String account_no, String trans_event_code,
			String trans_status, String balance, String currency_code) {
		this.account_no = account_no;
		this.trans_event_code = trans_event_code;
		this.trans_status = trans_status;
		this.balance = balance;
		this.currency_code = currency_code;
	}

	public String trans_id(int count) {
		String alpha_numeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String ans = "";
		while(count-- > 0) {
			int letter = (int) (Math.random() * alpha_numeric.length());
			ans += alpha_numeric.charAt(letter);
		}
		return ans;
	}

	@SuppressWarnings("unchecked")
	public void transaction_details() {
		this.trans_initial_date = LocalDateTime.now();
		this.trans_update_date = LocalDateTime.now();
		this.trans_id = trans_id(16);
		
		deposit_details = new JSONObject();
		
		deposit_details.put("account_no", this.account_no);
		deposit_details.put("transaction_id", this.trans_id);
		deposit_details.put("transaction_event_code", this.trans_event_code);
		deposit_details.put("transaction_initializatin_date", this.trans_initial_date);
		deposit_details.put("transaction_updated_date", this.trans_update_date);
		deposit_details.put("transaction_status", this.trans_status);
		
		JSONObject current_balance = new JSONObject();
		current_balance.put("value", this.balance);
		current_balance.put("current_code", this.currency_code);
		
		deposit_details.put("current_balance", current_balance);
		
		info = new JSONArray();
	    
	    info.add(deposit_details);
	    
	    System.out.println(info.toJSONString());
	   
	}
	
	public String get_details() {
		return info.toJSONString();
	}
	
	    
}
