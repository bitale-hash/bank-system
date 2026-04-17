package bankSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
 

@Entity
public class Transaction {
    @Id
    private String id;
    private String accountId;
    private double amount;
    private LocalDateTime timestamp;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction() {}

    public Transaction(String id, String accountId, double amount, TransactionType type) {
        this.id = id;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }
}