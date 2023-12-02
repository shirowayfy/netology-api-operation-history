package ru.netology.vChistyakov.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.vChistyakov.OperationHistoryApiApplicationTest;
import ru.netology.vChistyakov.config.OperationProperties;
import ru.netology.vChistyakov.domain.Currency;
import ru.netology.vChistyakov.domain.Operation;

import static org.junit.jupiter.api.Assertions.*;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    @Autowired
    private StatementService statementService;
    @Autowired
    private OperationProperties properties;

    @Test
    public void asyncInputOperationServiceWorksTest() throws InterruptedException {
        Operation operation = new Operation(5, 100, Currency.USD, "Restaurant", 1);

        asyncInputOperationService.offerOperation(operation);
        Thread.sleep(3L * properties.getSleepMilliSeconds());

        Operation operationOfService = statementService.getOperation(operation.getCustomerId(), 0);

        assertEquals(operation, operationOfService);
        assertEquals(operation.getId(), operationOfService.getId());
        assertEquals(operation.getSum(), operationOfService.getSum());
        assertEquals(operation.getCurrency(), operationOfService.getCurrency());
        assertEquals(operation.getMerchant(), operationOfService.getMerchant());
    }
}
