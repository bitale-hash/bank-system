package bankSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Account {

    @Id
    private String id;

    private String userId;

    private double balance;

    public Account() {}

    public Account(String id, String userId, double balance) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
    }
}