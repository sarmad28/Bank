package co.ubl.bank.Repository;

import co.ubl.bank.Entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaSpecificationExecutor<AuthEntity>, CrudRepository<AuthEntity,Long> {
    AuthEntity findByUserName(String username);
}
