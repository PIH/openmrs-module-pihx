package org.openmrs.module.pihx.change;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Ignore;
import org.springframework.stereotype.Component;

/**
 * Test Data Transaction Handler
 */
@Component
@Ignore
public class TestDataTransactionHandler implements DataTransactionHandler {

    private List<DataTransaction> transactions = new ArrayList<DataTransaction>();

    @Override
    public void handle(DataTransaction transaction) {
        transactions.add(transaction);
    }

    public List<DataTransaction> getTransactions() {
        return transactions;
    }

    public void reset() {
        transactions.clear();
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
            return mapper.writeValueAsString(this);
        }
        catch (Exception e) {
            throw new IllegalStateException("Unable to convert transactions to JSON", e);
        }
    }
}
