package com.vikio.bankapp.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.vikio.bankapp.model.Account;

public class InMemoryAccountDao implements AccountDao {
	
	private Map<String, Account> accountsMap;
	private List<Account> accountList;
	
	public InMemoryAccountDao() {
		accountList = new ArrayList<>();
		accountList.add(new Account("11111111-22222222", "HUF", new BigDecimal("150000.00")));
		accountList.add(new Account("22222222-33333333", "USD", new BigDecimal("1230")));
		
		accountsMap = Collections.synchronizedMap(accountList.stream()
				.collect(Collectors.toMap(Account::getAccountNo, a -> a)));
		
	}
	
	public Account findById(String id) {
		return accountsMap.get(id);
	}
	
	public List<Account> findAll(){	
		return accountList;
	}	
	
	public void updateAccount(Account account) {
		// persist ...
		accountsMap.put(account.getAccountNo(), account);
		
	}

}
