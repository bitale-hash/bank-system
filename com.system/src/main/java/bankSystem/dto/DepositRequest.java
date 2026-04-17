package bankSystem.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class DepositRequest {
    private UUID accountId;
    private BigDecimal amount;

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}