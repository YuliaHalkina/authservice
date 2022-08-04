package by.halkina.authservice.service;


import by.halkina.authservice.entity.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction update(Transaction transaction, Integer id);

    Transaction save(Transaction transaction);

    void delete(Integer id);

    List<Transaction> findAll();

    List<Transaction> findByActor(String actor);

    List<Transaction> findByType(String type);

    List<Transaction> findByTimestampAfter(String period);

}
