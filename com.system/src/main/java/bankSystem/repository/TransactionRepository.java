package bankSystem.repository;

import bankSystem.model.Transaction;
import java.util.*;

public class TransactionRepository {

    private final List<Transaction> transactions = new ArrayList<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> findAll() {
        return transactions;
    }

    public List<Transaction> findByAccountId(String accountId) {
        return transactions.stream()
                .filter(t ->
                        accountId.equals(t.getFromAccountId()) ||
                        accountId.equals(t.getToAccountId())
                )
                .toList();
    }
}