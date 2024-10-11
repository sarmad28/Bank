package co.ubl.bank.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@RequiredArgsConstructor
@Table(name = "ACCOUNTS")
public class AccountsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @ManyToMany(mappedBy = "accounts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AuthEntity> users = new ArrayList<>();

}
