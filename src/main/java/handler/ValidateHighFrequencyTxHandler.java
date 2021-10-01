package handler;

import model.Record;
import model.Transaction;
import model.TxRoot;
import pipeline.Handler;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static container.RegexContainer.isTxMatches;
import static container.Serializer.fromJson;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;
import static model.Violation.HIGH_FREQUENCY_SMALL_INTERVAL;

/***
 * Handler to validate when high frequency transaction occurred
 */
public final class ValidateHighFrequencyTxHandler implements Handler<Record, Record> {

    private static final int MAX_TX_OCCURRED_SUCCESSFULLY = 3;
    private static final int MINUTES_WINDOW = 2;

    @Override
    public Record process(Record input) {
        if (isTxMatches(input) && input.getAccount() != null) {
            if (input.getTxTraces().size() < MAX_TX_OCCURRED_SUCCESSFULLY) return input;
            var subList =
                    Stream.of(input.getTxTraces().stream().map(Transaction::getTime).collect(toList()),
                                    singletonList(fromJson(input.getInput(), TxRoot.class).getTransaction().getTime()))
                            .flatMap(Collection::stream)
                            .collect(toList());
            Collections.reverse(subList);
            subList = subList.subList(0, MAX_TX_OCCURRED_SUCCESSFULLY);
            if (subList.get(2).until(subList.get(1), MINUTES) + subList.get(2).until(subList.get(0), MINUTES) <= MINUTES_WINDOW)
                input.addViolation(HIGH_FREQUENCY_SMALL_INTERVAL.getName());

        }
        return input;
    }
}
