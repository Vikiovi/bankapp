package com.vikio.bankapp.model;

import java.math.BigDecimal;

public class Account {
	
	private String accountNo;
	private String currency;
	private BigDecimal balance;
	
    public Account(String accountNo, String currency, BigDecimal balance) {        
        this.accountNo = accountNo;        
        this.currency = currency;
        this.balance = balance;
    }
    
    public String getAccountNo() {
        return accountNo;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(this == obj) {
    		return true;
    	}
    	if(this.accountNo == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
    		return false;
    	}
        Account that = (Account)obj;
        return this.accountNo.equals(that.getAccountNo());
    }
    
    @Override
    public int hashCode() {
      return accountNo == null ? 0 : accountNo.hashCode();
    }
    
    @Override
    public String toString() {
        return "Account : {" +
                "accountNo=" + accountNo +
                ", currency='" + currency + '\'' +
                ", balance=" + balance + "}";
    }


}
