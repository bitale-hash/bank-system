package bankSystem.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bankSystem.model.Account;
import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    /* private final Map<String, Account> accounts = new HashMap<>();

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
    }*/
}