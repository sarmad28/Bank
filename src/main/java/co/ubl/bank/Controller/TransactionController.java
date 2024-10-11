package co.ubl.bank.Controller;

import co.ubl.bank.DTO.TransactionsDTO;
import co.ubl.bank.ExceptionHandler.CustomResponse;
import co.ubl.bank.Service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transactions")
    public ResponseEntity<?> transaction(TransactionsDTO transactionsDTO) {
        TransactionsDTO transaction = transactionService.createTransaction(transactionsDTO);
        throw new CustomResponse("Success", HttpStatus.OK, transaction);
    }
}
