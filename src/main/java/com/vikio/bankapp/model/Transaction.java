package com.vikio.bankapp.model;

import java.math.BigDecimal;

public class Transaction {
	
	private String accountNo;
	private String currency;
	private BigDecimal amount;
	private BigDecimal exRate;
	
	public Transaction() {}	
	
	public Transaction(String accountNo, String currency, BigDecimal amount, BigDecimal exRate) {
		this.accountNo = accountNo;
	    this.currency = currency;
	    this.amount = amount;
	    this.exRate = exRate;
	}
	    
	public String getAccountNo() {
	    return accountNo;
	}
	    
	public String getCurrency() {
	    return currency;
	}
	    
	public BigDecimal getAmount() {
	    return amount;
	}
	    
	public BigDecimal getExRate() {
	    return exRate;
	}
	    
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
	    }
	    if(this.accountNo == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
	    	return false;
	    }
	    Transaction that = (Transaction)obj;
	    return this.accountNo.equals(that.getAccountNo());
	}
	    
	@Override
	public int hashCode() {
		return accountNo == null ? 0 : accountNo.hashCode();
	}
	    
	// didn't bother with StringBuilder...
	@Override
	public String toString() {
		return "  Transaction : {" +
	            "accountNo=" + accountNo + 
	            ", currency='" + currency + '\'' +
	            ", amount=" + amount + '\'' +
	            ", exRate=" + exRate + "}";
	}

}


