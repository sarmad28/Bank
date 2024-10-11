package co.ubl.bank.Transformer;

import co.ubl.bank.DTO.AccountDTO;
import co.ubl.bank.Entity.AccountsEntity;

import java.util.List;


public class AccountTransformer {

    public static AccountsEntity getAccountEntity(AccountDTO accountDTO) {
        AccountsEntity accountsEntity = new AccountsEntity();
        accountsEntity.setId(accountDTO.getId());
        accountsEntity.setAccountType(accountDTO.getAccountType());
        return accountsEntity;
    }

    public static AccountDTO getAccountDTO(AccountsEntity entity) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(entity.getId());
        accountDTO.setAccountType(entity.getAccountType());
        return accountDTO;
    }
    public static List<AccountDTO> getAll(List<AccountsEntity> accountsEntityList) {
        List<AccountDTO> accountDTOS = null;
        for (AccountsEntity accountsEntity : accountsEntityList) {
            accountDTOS.add(AccountTransformer.getAccountDTO(accountsEntity));
        }
        return accountDTOS;
    }
}
