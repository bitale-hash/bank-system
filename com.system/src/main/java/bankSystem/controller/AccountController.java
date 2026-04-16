package bankSystem.controller;
 
import bankSystem.model.Account;
import bankSystem.service.BankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final BankService bankService;

    public AccountController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/create")
    public Account create(@RequestParam String userId) {
        return bankService.createAccount(userId);
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable String id) {
        return bankService.getAccount(id);
    }
}