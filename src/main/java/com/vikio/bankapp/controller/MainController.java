package com.vikio.bankapp.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.vikio.bankapp.dao.AccountDao;
import com.vikio.bankapp.dao.InMemoryAccountDao;
import com.vikio.bankapp.model.Account;
import com.vikio.bankapp.model.Transaction;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MainController {
	
	private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN; // bankers' rounding
	private static final int DECIMALS = 2;
	private static final int REPORT_FREQUENCY = 10;	

	// @Autowired
	// private AccountDao accountDao;   // in a Spring application AccountDao dependency implementation would be injected
	
	public void processTransactions() {
		// hard-coding InMemoryAccountDao implementation, in real environment it should be dependency injected:
		AccountDao accountDao = new InMemoryAccountDao();
		Map<Account, List<Transaction>> reportMap = new LinkedHashMap<>();

		List<Transaction> transactionList = loadTransactions();
		
		int stepCount = 0;
		for(Transaction trans : transactionList) {
			String accountNo = trans.getAccountNo();
			Account account = accountDao.findById(accountNo);
			
			if(account!= null) {
				BigDecimal transAmount = trans.getAmount();
				if(trans.getExRate() != null) {										
					transAmount = trans.getExRate().multiply(transAmount).setScale(DECIMALS, ROUNDING_MODE);					
				}				
				account.setBalance(account.getBalance().add(transAmount));
				// save in database:
				accountDao.updateAccount(account);
				reportMap.computeIfAbsent(account, (unused) -> new ArrayList<>()).add(trans);
				stepCount++;
			} else {
				System.err.println("Warning ! Non-existent account ! " + account);
			}
			if (stepCount % REPORT_FREQUENCY == 0) {
				System.out.println(".............. printing report after each 10-th transaction:");				
				printReport(reportMap);
				//reportMap.clear();
		    }
		}
		
	}	

	//private void printReport(List<Predicate<Transaction>> transPredicateList){
	private void printReport(Map<Account, List<Transaction>> reportMap){
		for(Account account : reportMap.keySet()){	
			System.out.println(account);
			for(Transaction trans : reportMap.get(account)){
				System.out.println(trans);
			}
		}		
	}
	
	private List<Transaction> loadTransactions() {		
		List<Transaction> transactionList = new ArrayList<>();		
		ObjectMapper objectMapper = new ObjectMapper();
	    try {	    	
	    	transactionList = Arrays.asList(objectMapper.readValue(
	    			ClassLoader.getSystemClassLoader().getResourceAsStream("transactions.json"),	    			
	    			Transaction[].class));    	
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    //System.out.println(transactionList);
		return transactionList;
	}

}