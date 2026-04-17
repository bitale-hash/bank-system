package bankSystem.service;
import org.springframework.stereotype.Service;
import bankSystem.model.Account;
import bankSystem.model.Transaction;
import bankSystem.model.TransactionType;
import bankSystem.repository.AccountRepository;
import bankSystem.repository.TransactionRepository;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }


    public Account createAccount(String userId) {
        Account account = new Account(userId);
        accountRepository.save(account);
        return account;
    }
    
    public void deposit(String accountId, double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));;

        if (account == null) 
            throw new RuntimeException("Account not found");
    
        account.deposit(amount);
        Transaction tx = new Transaction( null, accountId, amount, TransactionType.DEPOSIT);
        transactionRepository.save(tx);
    }

    public void withdraw(String accountId, double amount) {

        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));;

        if (account == null) 
            throw new RuntimeException("Account not found");
    

        if (account.getBalance() < amount) 
            throw new RuntimeException("Insufficient balance");

        account.withdraw(amount);
        Transaction tx = new Transaction( accountId, null, amount, TransactionType.WITHDRAW);
        transactionRepository.save(tx);
    }
    public Account getAccount(String id) {
        return accountRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}