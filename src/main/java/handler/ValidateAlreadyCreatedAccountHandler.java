package handler;

import container.Serializer;
import model.AccountRoot;
import model.Record;
import pipeline.Handler;

import static container.RegexContainer.isAccountMatches;
import static model.Violation.ACCOUNT_ALREADY_INITIALIZED;

/***
 * Handler to validate when already created an account
 */
public final class ValidateAlreadyCreatedAccountHandler implements Handler<Record, Record> {
    @Override
    public Record process(Record input) {

        if (isAccountMatches(input)) {
            if (input.getAccount() == null) {
                var account = Serializer.fromJson(input.getInput(), AccountRoot.class);
                input.setAccount(account.getAccount());
            } else
                input.addViolation(ACCOUNT_ALREADY_INITIALIZED.getName());
        }
        return input;
    }
}
