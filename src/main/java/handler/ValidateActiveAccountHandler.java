package handler;

import model.Record;
import model.Violation;
import pipeline.Handler;

import static java.lang.Boolean.FALSE;

import static container.RegexContainer.isTxMatches;

/***
 * Handler to validate active account
 */
public final class ValidateActiveAccountHandler implements Handler<Record, Record> {
    @Override
    public Record process(Record input) {
        if (input.getAccount() != null && isTxMatches(input) && FALSE.equals(input.getAccount().getActiveCard()))
            input.addViolation(Violation.CARD_NOT_ACTIVE.getName());
        return input;
    }
}
