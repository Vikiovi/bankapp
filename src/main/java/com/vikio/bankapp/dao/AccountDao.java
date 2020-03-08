package com.vikio.bankapp.dao;

import java.util.List;

import com.vikio.bankapp.model.Account;

public interface AccountDao {
	
	public Account findById(String id);
	public List<Account> findAll();
	public void updateAccount(Account account);	

}
