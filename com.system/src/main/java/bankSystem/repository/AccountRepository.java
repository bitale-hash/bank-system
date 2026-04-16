package bankSystem.repository;
import org.springframework.stereotype.Repository;
import bankSystem.model.Account;
import java.util.*;

@Repository
public class AccountRepository {

    private final Map<String, Account> accounts = new HashMap<>();

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }

    public Account findById(String id) {
        return accounts.get(id);
    }

    public List<Account> findByUserId(String userId) {
        return accounts.values()
                .stream()
                .filter(a -> a.getUserId().equals(userId))
                .toList();
    }
}