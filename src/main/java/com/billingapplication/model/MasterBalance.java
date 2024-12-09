package com.billingapplication.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterBalance {
    
    private double balance;

    public MasterBalance(double initialBalance) {
        this.balance = initialBalance;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void deductBalance(double amount) {
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.balance -= amount;
    }

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
    
    
}
