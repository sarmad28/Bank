package co.ubl.bank.Service;

import co.ubl.bank.DTO.TransactionsDTO;
import co.ubl.bank.Entity.TransactionsEntity;

public interface TransactionService {

    TransactionsDTO createTransaction(TransactionsDTO transactionsDTO);

    TransactionsDTO updateTransaction(TransactionsDTO transactionsDTO);

    TransactionsDTO delete(TransactionsDTO transactionsDTO);

    TransactionsDTO findById(TransactionsDTO transactionsDTO);
}
