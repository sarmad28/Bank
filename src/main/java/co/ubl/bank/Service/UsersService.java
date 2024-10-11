package co.ubl.bank.Service;

import co.ubl.bank.DTO.*;

import java.util.List;

public interface UsersService {
    UsersDTO createUser(UsersDTO usersDTO);

    UsersDTO update(UsersDTO usersDTO);

    UsersDTO delete(Long id);

    UsersDTO findById(Long id);

    List<UsersDTO> findAll();

    UsersDTO findByUserName(UsersDTO usersDTO);

    UsersDTO balanceInfo(EnquiryRequest enquiryRequest);

    UsersDTO accountExists(String accountNumber);

    BankResponse credit(CreditDebitRequest creditDebitRequest);

    BankResponse debit(CreditDebitRequest creditDebitRequest);

    BankResponse transfer(TransferRequest transferRequest);
}
