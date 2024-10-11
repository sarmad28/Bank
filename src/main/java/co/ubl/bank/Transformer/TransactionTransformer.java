package co.ubl.bank.Transformer;

import co.ubl.bank.DTO.TransactionsDTO;
import co.ubl.bank.Entity.TransactionsEntity;

public class TransactionTransformer {

    public static TransactionsDTO getTransactionsDTO(TransactionsEntity transactionsEntity) {
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setTransactionId(transactionsEntity.getTransactionId());
        transactionsDTO.setTransactionAmount(transactionsEntity.getTransactionAmount());
        transactionsDTO.setAccountNumber(transactionsEntity.getAccountNumber());
        transactionsDTO.setTransactionType(transactionsEntity.getTransactionType());
        transactionsDTO.setTransactionDate(transactionsEntity.getTransactionDate());
        return transactionsDTO;
    }

    public static TransactionsEntity getTransactionEntity(TransactionsDTO transactionsDTO) {
        TransactionsEntity transactions = new TransactionsEntity();
        transactions.setTransactionId(transactionsDTO.getTransactionId());
        transactions.setTransactionAmount(transactionsDTO.getTransactionAmount());
        transactions.setAccountNumber(transactionsDTO.getAccountNumber());
        transactions.setTransactionType(transactionsDTO.getTransactionType());
        transactions.setTransactionDate(transactionsDTO.getTransactionDate());
        return transactions;
    }
}
