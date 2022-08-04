package by.halkina.authservice.controller;

import by.halkina.authservice.entity.Transaction;
import by.halkina.authservice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public Transaction save(@Valid @RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @GetMapping
    public List<Transaction> findAll(HttpServletRequest request) {
        String actor = request.getParameter("actor");
        if (actor != null && !actor.isEmpty()) {
            return transactionService.findByActor(actor);
        }
        String type = request.getParameter("type");
        if (type != null && !type.isEmpty()) {
            return transactionService.findByType(type);
        }
        String fromDate = request.getParameter("from");
        if (fromDate != null && !fromDate.isEmpty()) {
            return transactionService.findByTimestampAfter(fromDate);
        }
        return transactionService.findAll();
    }

    @PutMapping("/{id}")
    public Transaction update(@RequestBody Transaction transaction,
           @PathVariable("id") Integer transactionId) {
        return transactionService.update(transaction, transactionId);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer transactionId) {
        transactionService.delete(transactionId);
        return "Deleted Successfully";
    }
}
