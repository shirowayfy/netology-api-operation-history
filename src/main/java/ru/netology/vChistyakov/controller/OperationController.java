package ru.netology.vChistyakov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.vChistyakov.domain.Operation;
import ru.netology.vChistyakov.service.AsyncInputOperationService;
import ru.netology.vChistyakov.service.StatementService;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {
    private final AsyncInputOperationService asyncInputOperationService;
    private final StatementService statementService;

    @PostMapping
    public Operation postOperation(@RequestBody Operation operation) {
        asyncInputOperationService.offerOperation(operation);
        return operation;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Iterable<Operation>> getClientOperations(@PathVariable Integer customerId) {
        List<Operation> operations = statementService.getCustomerOperations(customerId);
        return operations == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(operations, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable Integer id) {
        for (List<Operation> operations : statementService.getStatement().values()){
            for (Operation operation : operations){
                if (operation.getId().equals(id))
                    return new ResponseEntity<>(operation, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}