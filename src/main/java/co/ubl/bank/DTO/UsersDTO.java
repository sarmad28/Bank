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
public class UsersDTO {
    private Long id;
    private String accountNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private BigDecimal accountBalance;
    private String mobileNumber;
    private String status;
}
