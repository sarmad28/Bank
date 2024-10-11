package co.ubl.bank.ServiceImpl;

import co.ubl.bank.DTO.TransactionsDTO;
import co.ubl.bank.Entity.TransactionsEntity;
import co.ubl.bank.Repository.TransactionRepository;
import co.ubl.bank.Service.TransactionService;
import co.ubl.bank.Transformer.TransactionTransformer;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionsDTO createTransaction(TransactionsDTO transactionsDTO) {
        TransactionsEntity transactionEntity = TransactionTransformer.getTransactionEntity(transactionsDTO);
        return TransactionTransformer.getTransactionsDTO(transactionRepository.save(transactionEntity));
    }

    @Override
    public TransactionsDTO updateTransaction(TransactionsDTO transactionsDTO) {
        return null;
    }

    @Override
    public TransactionsDTO delete(TransactionsDTO transactionsDTO) {
        return null;
    }

    @Override
    public TransactionsDTO findById(TransactionsDTO transactionsDTO) {
        return null;
    }
}
