package bankSystem.controller;

import org.springframework.web.bind.annotation.*;
import bankSystem.service.BankService;
import bankSystem.model.Account;
import bankSystem.dto.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bank")
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    // 🟢 Create account
    @PostMapping("/account")
    public Account createAccount(@RequestParam String userId) {
        return bankService.createAccount(userId);
    }

    // 🟢 Get account
    @GetMapping("/account/{id}")
    public Account getAccount(@PathVariable UUID id) {
        return bankService.getAccount(id);
    }

    // 💰 Deposit
    @PostMapping("/deposit")
    public void deposit(@RequestBody DepositRequest request) {
        bankService.deposit(request.getAccountId(), request.getAmount());
    }

    // 💸 Withdraw
    @PostMapping("/withdraw")
    public void withdraw(@RequestBody WithdrawRequest request) {
        bankService.withdraw(request.getAccountId(), request.getAmount());
    }

    // 🔁 Transfer
    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest request) {
        bankService.transfer(
                request.getFromAccountId(),
                request.getToAccountId(),
                request.getAmount()
        );
    }
}