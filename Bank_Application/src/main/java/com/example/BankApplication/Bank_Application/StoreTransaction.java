package com.example.BankApplication.Bank_Application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class StoreTransaction {
	
	public void store_transaction(String info) {
		try {
			FileWriter file = new FileWriter("/home/kirito/Temporary/transaction_stream.json");
			file.write(info);
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
