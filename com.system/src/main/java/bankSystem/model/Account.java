package bankSystem.model;

import java.util.UUID;

public class Account {

    private String id;
    private String userId;
    private double balance;

    public Account(String userId) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.balance = 0.0;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }
}