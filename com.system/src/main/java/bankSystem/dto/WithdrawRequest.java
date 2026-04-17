package bankSystem.dto;

import java.math.BigDecimal;
import java.util.UUID;

public class WithdrawRequest {
    private UUID accountId;
    private BigDecimal amount;

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}