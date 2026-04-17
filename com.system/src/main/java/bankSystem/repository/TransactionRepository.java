package bankSystem.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bankSystem.model.Transaction;
import java.util.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    /*
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
    */
}