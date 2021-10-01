package handler;

import model.Record;
import pipeline.Handler;

import static container.RegexContainer.isTxMatches;
import static model.Violation.ACCOUNT_NOT_INITIALIZED;
/***
 * Handler to validate when and account is not initialized
 */
public final class ValidateInitAccountHandler implements Handler<Record, Record> {

    @Override
    public Record process(Record input) {
        if (input.getAccount() == null && isTxMatches(input))
            input.addViolation(ACCOUNT_NOT_INITIALIZED.getName());
        return input;
    }
}
