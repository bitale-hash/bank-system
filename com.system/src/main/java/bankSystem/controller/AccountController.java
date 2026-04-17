package bankSystem.controller;

import bankSystem.dto.AccountResponse;
import bankSystem.model.Account;
import bankSystem.service.BankService;
import org.springframework.web.bind.annotation.*;
import bankSystem.dto.CreateAccountRequest;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final BankService bankService;

    public AccountController(BankService bankService) {
        this.bankService = bankService;
    }

    // CREATE ACCOUNT
    @PostMapping
    public AccountResponse create(@RequestBody CreateAccountRequest request){
        return bankService.createAccount(request.getUserId());
    }

    // GET ACCOUNT (DTO)
    @GetMapping("/{id}")
    public AccountResponse getAccount(@PathVariable UUID id) {
        return bankService.getAccount(id);
    }
}