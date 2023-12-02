package ru.netology.vChistyakov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vChistyakov.OperationHistoryApiApplicationTest;
import ru.netology.vChistyakov.domain.Currency;
import ru.netology.vChistyakov.domain.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;

    @Test
    public void saveInStatementServiceTest() {
        int operationId = 1;
        int operationSum = 300;
        Currency operationCurrency = Currency.RUB;
        String operationMerchant = "Restaurant";
        int customerId = 3;

        Operation operation = new Operation(operationId, operationSum, operationCurrency, operationMerchant, customerId);
        statementService.setOperation(customerId, operation);
        Operation operationOfService = statementService.getOperation(customerId, 0);

        assertEquals(operation, operationOfService);
        assertEquals(operationId, operationOfService.getId());
        assertEquals(operationSum, operationOfService.getSum());
        assertEquals(operationCurrency, operationOfService.getCurrency());
        assertEquals(operationMerchant, operationOfService.getMerchant());
        assertEquals(customerId, operationOfService.getCustomerId());
    }
}