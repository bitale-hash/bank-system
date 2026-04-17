package bankSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bankSystem.model.Account;
import bankSystem.model.Transaction;
import bankSystem.model.TransactionType;
import bankSystem.repository.AccountRepository;
import bankSystem.repository.TransactionRepository;
import bankSystem.dto.AccountResponse;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public AccountResponse createAccount(String userId) {
        Account account = new Account(userId, BigDecimal.ZERO);
        Account saved = accountRepository.save(account);

        return new AccountResponse(saved.getId(), saved.getBalance());
    }

    @Transactional
    public void deposit(UUID accountId, BigDecimal amount) {
        
        

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        validateAmount(amount);

        account.setBalance(account.getBalance().add(amount));

        Transaction tx = new Transaction(
                account,
                amount,
                TransactionType.DEPOSIT
        );

        transactionRepository.save(tx);
    }

    @Transactional
    public void withdraw(UUID accountId, BigDecimal amount) {
           
        Account account= getAccount(accountId);
        validateAmount(amount);

        validateSufficientBalance(account, amount);
         

        account.setBalance(account.getBalance().subtract(amount));

        Transaction tx = new Transaction(
                account,
                amount,
                TransactionType.WITHDRAW
        );

        transactionRepository.save(tx);
    }
    @Transactional
    public void transfer(UUID fromAccountId, UUID toAccountId, BigDecimal amount) {
        //Controllo che gli id siano diversi
        if (fromAccountId.equals(toAccountId)) {
            throw new RuntimeException("Cannot transfer to the same account");
        }
        validateAmount(amount);

        //prendo gli account grazie agli id 
        
            Account fromAccount =getAccount(fromAccountId);
            Account toAccount =getAccount(toAccountId);
        
        
        //controllo che ci siano i soldi per fare il versamento
        validateSufficientBalance(fromAccount, amount);

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        Transaction withdrawTx = new Transaction(
                fromAccount,
                amount,
                TransactionType.WITHDRAW
        );
        transactionRepository.save(withdrawTx);

        toAccount.setBalance(toAccount.getBalance().add(amount));
        Transaction depositTx = new Transaction(
                toAccount,
                amount,
                TransactionType.DEPOSIT
        );
        transactionRepository.save(depositTx);
        //rindondanti, ci pensa @Transactional a salvare
        //accountRepository.save(fromAccount);
        //accountRepository.save(toAccount);
    }

    public AccountResponse getAccount(UUID id) {

        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        return new AccountResponse( account.getId(), account.getBalance());
    }
    private void validateAmount(BigDecimal amount){
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) 
            throw new RuntimeException("Amount must be greater than zero");
        
    }
    private void validateSufficientBalance(Account account, BigDecimal amount) {
        if (account.getBalance().compareTo(amount) < 0) 
         throw new RuntimeException("Insufficient balance");
    
    }
    private Account getAccount(UUID id) {
        return accountRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}