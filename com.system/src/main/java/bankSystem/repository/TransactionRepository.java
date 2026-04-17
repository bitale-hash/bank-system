package bankSystem.repository;

import bankSystem.model.Account;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import bankSystem.model.Transaction;

import java.util.UUID;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByAccount(Account account);

}