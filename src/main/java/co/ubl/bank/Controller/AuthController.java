package co.ubl.bank.Controller;

import co.ubl.bank.DTO.AccountDTO;
import co.ubl.bank.DTO.AuthDTO;
import co.ubl.bank.ExceptionHandler.CustomResponse;
import co.ubl.bank.Service.AccountService;
import co.ubl.bank.Service.AuthService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody AuthDTO authDTO) {
        if (authDTO.getAccountDTOS() != null) {
            for (AccountDTO accountDTO : authDTO.getAccountDTOS()) {
                AccountDTO accountById = accountService.findAccountById(accountDTO.getId());
                if (accountById == null) {
                    throw new CustomResponse("Account Not Found: " + accountDTO.getId(), HttpStatus.OK);
                }
            }
        }
        AuthDTO user = authService.createUser(authDTO);
        throw new CustomResponse("User created", HttpStatus.OK, user);
    }

    @PostMapping("/getAll")
    public ResponseEntity<?> getAll() {
        List<AuthDTO> all = authService.findAll();
        throw new CustomResponse("Success", HttpStatus.OK, all);
    }

    @PostMapping("/findById{id}")
    public ResponseEntity<?> findById(@PathParam("id") Long id) {
        AuthDTO byId = authService.findById(id);
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }
}
