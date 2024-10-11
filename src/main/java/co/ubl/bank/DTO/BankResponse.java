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
public class BankResponse {
    private String accountNumber;
    private String accountHolderName;
    private BigDecimal accountBalance;
    private String bankName;
}
