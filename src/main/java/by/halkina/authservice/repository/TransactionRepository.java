package by.halkina.authservice.repository;

import by.halkina.authservice.entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

    List<Transaction> findAllByActor(String actor);

    List<Transaction> findAllByType(String type);

    List<Transaction> findAllByTimestampAfter(Timestamp date);

}
