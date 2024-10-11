package co.ubl.bank.Repository;

import co.ubl.bank.Entity.TransactionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionsEntity, UUID>, JpaSpecificationExecutor<TransactionsEntity> {
}
