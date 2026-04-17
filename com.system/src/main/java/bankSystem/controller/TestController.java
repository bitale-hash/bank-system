package bankSystem.controller;
 
import bankSystem.model.Account;
import bankSystem.service.BankService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String hello() {
        return "OK FUNZIONA";
    }
}