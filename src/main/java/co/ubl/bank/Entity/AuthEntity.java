package co.ubl.bank.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auth")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Transient
    @Column(name = "TOKEN")
    private String token;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_accounts", // Join table name
            joinColumns = @JoinColumn(name = "user_id"), // Foreign key to User
            inverseJoinColumns = @JoinColumn(name = "account_id") // Foreign key to Account
    )
    private List<AccountsEntity> accounts = new ArrayList<>();

}

