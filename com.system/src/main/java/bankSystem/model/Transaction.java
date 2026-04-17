package bankSystem.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne  // significa Un account → tante transazioni
✔️               // Una transazione → un solo account
    private Account account;

    private BigDecimal amount;

    private LocalDateTime timestamp;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction() {}

    public Transaction(Account account, BigDecimal amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}