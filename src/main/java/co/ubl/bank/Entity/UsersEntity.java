package co.ubl.bank.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String accountNumber;
    private BigDecimal accountBalance;
    private String mobileNumber;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private String createdTime;
}
