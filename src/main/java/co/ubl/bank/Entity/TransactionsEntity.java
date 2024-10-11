package co.ubl.bank.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTIONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;
    private String accountNumber;
    private String transactionType;
    private BigDecimal transactionAmount;
    @Temporal(TemporalType.TIMESTAMP)
    private String transactionDate;
}
