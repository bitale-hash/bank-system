package bankSystem.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class AccountResponse {

    private UUID id;
    private BigDecimal balance;

    public AccountResponse(UUID id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}