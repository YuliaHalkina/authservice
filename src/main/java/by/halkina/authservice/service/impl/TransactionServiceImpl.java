package by.halkina.authservice.service.impl;

import by.halkina.authservice.entity.Transaction;
import by.halkina.authservice.repository.TransactionRepository;
import by.halkina.authservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction update(Transaction transaction, Integer id) {
        Optional<Transaction> dbTransaction = transactionRepository.findById(id);
        if (dbTransaction.isEmpty()) {
            log.warn("Transaction with id {} not found. New transaction will be saved", id);
            return transactionRepository.save(transaction);
        }
        Transaction transactionToUpdate = dbTransaction.get();
        if (transaction.getTimestamp() != null) {
            transactionToUpdate.setTimestamp(transaction.getTimestamp());
        }
        if (transaction.getActor() != null) {
            transactionToUpdate.setActor(transaction.getActor());
        }
        if (transaction.getType() != null) {
            transactionToUpdate.setType(transaction.getType());
        }
        if (transaction.getTransactionData() != null) {
            transactionToUpdate.setTransactionData(transaction.getTransactionData());
        }
        return transactionRepository.save(transactionToUpdate);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(Integer id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public List<Transaction> findAll() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findByActor(String actor) {
        return transactionRepository.findAllByActor(actor);
    }

    @Override
    public List<Transaction> findByType(String type) {
        return transactionRepository.findAllByType(type);
    }

    @Override
    public List<Transaction> findByTimestampAfter(String date) {
        try {
            Timestamp ts = Timestamp.valueOf(addTime(date));
            return transactionRepository.findAllByTimestampAfter(ts);
        } catch (IllegalArgumentException e) {
            log.error("Date format must be yyyy-mm-dd");
            return Collections.emptyList();
        }
    }

    private String addTime(String date) {
        return date + " 00:00:00";
    }
}
