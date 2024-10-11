package co.ubl.bank.Repository;

import co.ubl.bank.Entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<AccountsEntity, Long>, JpaSpecificationExecutor<AccountsEntity> {

    AccountsEntity findAccountsByAccountType(String accountHolderName);
}
