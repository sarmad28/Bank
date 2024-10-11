package co.ubl.bank.Transformer;

import co.ubl.bank.DTO.AccountDTO;
import co.ubl.bank.DTO.AuthDTO;
import co.ubl.bank.Entity.AccountsEntity;
import co.ubl.bank.Entity.AuthEntity;

import java.util.ArrayList;
import java.util.List;


public class AuthTransformer {
    public static AuthDTO getUserDTO(AuthEntity authEntity) {
        AuthDTO authDTO = new AuthDTO();
        authDTO.setId(authEntity.getId());
        authDTO.setUserName(authEntity.getUserName());
        authDTO.setPassword(authEntity.getPassword());
        authDTO.setToken(authEntity.getToken());
        if (authEntity.getAccounts() != null) {
            List<AccountDTO> accountDTOS = new ArrayList<>();
            for (AccountsEntity entity : authEntity.getAccounts()) {
                accountDTOS.add(AccountTransformer.getAccountDTO(entity));
            }
            authDTO.setAccountDTOS(accountDTOS);
        }
        return authDTO;
    }

    public static AuthEntity getUserEntity(AuthDTO authDTO) {
        AuthEntity authEntity = new AuthEntity();
        authEntity.setId(authDTO.getId());
        authEntity.setUserName(authDTO.getUserName());
        authEntity.setPassword(authDTO.getPassword());
        authEntity.setToken(authDTO.getToken());
        if (authDTO.getAccountDTOS().size() > 0 && authDTO.getAccountDTOS() != null) {
            List<AccountsEntity> accountsEntityEntities = new ArrayList<>();
            for (AccountDTO dto : authDTO.getAccountDTOS()) {
                AccountsEntity accountEntity = AccountTransformer.getAccountEntity(dto);
                accountsEntityEntities.add(accountEntity);
            }
            authEntity.setAccounts(accountsEntityEntities);
        }
        return authEntity;
    }

    public static List<AuthDTO> getAll(List<AuthEntity> userEntities) {
        List<AuthDTO> list = new ArrayList<>();
        for (AuthEntity authEntity : userEntities) {
            list.add(AuthTransformer.getUserDTO(authEntity));
        }
        return list;
    }
}
