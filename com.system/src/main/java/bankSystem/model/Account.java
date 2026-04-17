package bankSystem.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private UUID id;

    private String userId;

    private BigDecimal balance;

    public Account() {}

    public Account(String userId, BigDecimal balance) {
        this.userId = userId;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}