package co.ubl.bank.DTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {

    private Long id;
    private String userName;
    private String password;
    private String token;
    private List<AccountDTO> accountDTOS;
}


