package co.ubl.bank.Service;

import co.ubl.bank.DTO.AccountDTO;

import java.util.List;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO update(AccountDTO accountDTO);

    AccountDTO findAccountById(Long accountId);

    AccountDTO deleteAccountById(Long accountId);

    AccountDTO findByAccountName(String accountName);

    List<AccountDTO> getAllAccounts();
}
