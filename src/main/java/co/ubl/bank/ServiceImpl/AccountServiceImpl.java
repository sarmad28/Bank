package co.ubl.bank.ServiceImpl;

import co.ubl.bank.DTO.AccountDTO;
import co.ubl.bank.Entity.AccountsEntity;
import co.ubl.bank.ExceptionHandler.CustomResponse;
import co.ubl.bank.Repository.AccountRepository;
import co.ubl.bank.Service.AccountService;
import co.ubl.bank.Transformer.AccountTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        AccountsEntity accountEntity = AccountTransformer.getAccountEntity(accountDTO);
        AccountsEntity save = accountRepository.save(accountEntity);
        return AccountTransformer.getAccountDTO(save);
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        return null;
    }

    @Override
    public AccountDTO findAccountById(Long accountId) {
        Optional<AccountsEntity> byId = accountRepository.findById(accountId);
        if (byId.isEmpty()) {
            throw new CustomResponse("Account Not Found: ", HttpStatus.OK);
        }
        return AccountTransformer.getAccountDTO(byId.get());
    }

    @Override
    public AccountDTO deleteAccountById(Long accountId) {
        return null;
    }

    @Override
    public AccountDTO findByAccountName(String accountName) {
        AccountsEntity accountsEntityByAccountHolderName = accountRepository.findAccountsByAccountType(accountName);
        return AccountTransformer.getAccountDTO(accountsEntityByAccountHolderName);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        Iterable<AccountsEntity> all = accountRepository.findAll();
        return AccountTransformer.getAll((List<AccountsEntity>) all);
    }
}
