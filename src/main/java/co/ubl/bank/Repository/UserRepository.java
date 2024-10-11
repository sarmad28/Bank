package co.ubl.bank.Repository;

import co.ubl.bank.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaSpecificationExecutor<UsersEntity>, CrudRepository<UsersEntity, Long> {

    UsersEntity findFirstByFirstName(String firstName);

    UsersEntity findByAccountNumber(String accountNumber);

    UsersEntity findFirstByAccountNumber(String accountNumber);
}
