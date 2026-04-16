package bankSystem.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    private String id;

    private String fromAccountId; // può essere null (deposito)
    private String toAccountId;   // può essere null (prelievo)

    private double amount;

    private LocalDateTime timestamp;

    private TransactionType type;

    public Transaction(String fromAccountId, String toAccountId, double amount, TransactionType type) {
        this.id = UUID.randomUUID().toString();
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccountId() {
        return toAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }
}