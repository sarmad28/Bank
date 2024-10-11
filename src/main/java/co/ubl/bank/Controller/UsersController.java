package co.ubl.bank.Controller;

import co.ubl.bank.DTO.*;
import co.ubl.bank.ExceptionHandler.CustomResponse;
import co.ubl.bank.Service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UsersService userService;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UsersDTO usersDTO) {
        UsersDTO user = userService.createUser(usersDTO);
        throw new CustomResponse("User has been created", HttpStatus.OK, user);
    }

    @PostMapping("/balanceInfo")
    public ResponseEntity<?> balanceInfo(@RequestBody EnquiryRequest enquiryRequest) {
        UsersDTO user = userService.balanceInfo(enquiryRequest);
        BankResponse bankResponse = new BankResponse();
        bankResponse.setBankName("UBL");
        bankResponse.setAccountBalance(user.getAccountBalance());
        bankResponse.setAccountNumber(user.getAccountNumber());
        bankResponse.setAccountHolderName(user.getFirstName() + " " + user.getLastName());
        throw new CustomResponse("Success", HttpStatus.OK, bankResponse);
    }

    @PostMapping("/credit")
    public ResponseEntity<?> creditRequest(@RequestBody CreditDebitRequest creditDebitRequest) {
        BankResponse b = userService.credit(creditDebitRequest);
        throw new CustomResponse("Dear Customer you account " + creditDebitRequest.getAccountNumber() + " is credited by PKR " + creditDebitRequest.getAmount(), HttpStatus.OK, b);
    }

    @PostMapping("/debit")
    public ResponseEntity<?> debitRequest(@RequestBody CreditDebitRequest creditDebitRequest) {
        BankResponse b = userService.debit(creditDebitRequest);
        throw new CustomResponse("Dear Customer you account " + creditDebitRequest.getAccountNumber() + " is debited by PKR " + creditDebitRequest.getAmount(), HttpStatus.OK, b);
    }

    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransferRequest transferRequest) {
        BankResponse b = userService.transfer(transferRequest);
        throw new CustomResponse("Dear Customer your account " + transferRequest.getFromAccountNumber() + " is transferred to account " + transferRequest.getToAccountNumber() + " by PKR " + transferRequest.getAmount(), HttpStatus.OK, b);
    }
    @PostMapping("/getAll")
    public ResponseEntity<?> getAllUsers() {
        List<UsersDTO> all = userService.findAll();
        throw new CustomResponse("Success", HttpStatus.OK, all);
    }
}
