package co.ubl.bank.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDTO {

    private String transactionId;
    private String accountNumber;
    private String transactionType;
    private BigDecimal transactionAmount;
    private String transactionDate;
}
