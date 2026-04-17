package bankSystem.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bankSystem.model.Account;
 

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    
}