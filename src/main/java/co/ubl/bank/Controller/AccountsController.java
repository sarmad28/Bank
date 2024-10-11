package co.ubl.bank.Controller;

import co.ubl.bank.DTO.AccountDTO;
import co.ubl.bank.Entity.AccountsEntity;
import co.ubl.bank.Repository.AccountRepository;
import co.ubl.bank.Service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Accounts")
public class AccountsController {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    public AccountsController(AccountRepository accountRepository, AccountService accountService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;   // TODO Auto increment the account service counter here to avoid collisions with other accounts that are already in use  by  another account service account service
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAccounts(@RequestBody @Valid AccountDTO accountDTO) {
        // Implement logic to create accounts
        AccountsEntity accountsEntityByAccountHolderName = accountRepository.findAccountsByAccountType(accountDTO.getAccountType());
        if (accountsEntityByAccountHolderName != null) {
            return ResponseEntity.badRequest().body("Account with same account holder name already exists");
        }
        AccountDTO account = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }
}
