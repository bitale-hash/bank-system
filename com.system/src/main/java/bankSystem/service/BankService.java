package bankSystem.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bankSystem.model.Account;
import bankSystem.model.Transaction;
import bankSystem.model.TransactionType;
import bankSystem.repository.AccountRepository;
import bankSystem.repository.TransactionRepository;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class BankService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository,
                       TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Account createAccount(String userId) {
        Account account = new Account(userId, BigDecimal.ZERO);
        return accountRepository.save(account);
    }

    @Transactional
    public void deposit(UUID accountId, BigDecimal amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

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

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

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
        //prendo gli account grazie agli id 
        Account fromAccount = accountRepository.findById(fromAccountId)
            .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account toAccount = accountRepository.findById(toAccountId)
            .orElseThrow(() -> new RuntimeException("Destination account not found"));
        
        //controllo che ci siano i soldi per fare il versamento
        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

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

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public Account getAccount(UUID id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}