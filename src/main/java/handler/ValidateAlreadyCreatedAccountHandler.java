package handler;

import model.AccountRoot;
import model.Record;
import pipeline.Handler;

import java.util.Collection;
import java.util.stream.Stream;

import static container.RegexContainer.isAccountMatches;
import static container.Serializer.fromJson;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static model.Violation.ACCOUNT_ALREADY_INITIALIZED;

/***
 * Handler to validate when already created an account
 */
public final class ValidateAlreadyCreatedAccountHandler implements Handler<Record, Record> {
    @Override
    public Record process(Record input) {
        Record record;
        if (isAccountMatches(input)) {
            if (input.getAccount() == null) {
                var account = fromJson(input.getInput(), AccountRoot.class);
                record = new Record(
                        account.getAccount(),
                        input.getViolations(),
                        input.getTxTraces(),
                        input.getInput()
                );
            } else
                record = new Record(
                        input.getAccount(),
                        Stream.of(input.getViolations(), singletonList(ACCOUNT_ALREADY_INITIALIZED.getName()))
                                .flatMap(Collection::stream)
                                .collect(toList()),
                        input.getTxTraces(),
                        input.getInput()
                );
            return record;

        }
        return input;
    }
}
