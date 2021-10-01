package handler;

import container.Serializer;
import model.Record;
import model.TxRoot;
import model.Violation;
import pipeline.Handler;

import static java.time.temporal.ChronoUnit.MINUTES;

import static container.RegexContainer.isTxMatches;

/***
 * Handler performing a transaction
 */
public final class PerformTxHandler implements Handler<Record, Record> {

    @Override
    public Record process(Record input) {
        if (input.getAccount() != null && isTxMatches(input)) {
            var tx = Serializer.fromJson(input.getInput(), TxRoot.class);

            if (input.getTxTraces().stream()
                    .anyMatch(txRecorded -> txRecorded.getMerchant().equals(tx.getTransaction().getMerchant()) &&
                            txRecorded.getAmount().equals(tx.getTransaction().getAmount()) &&
                            txRecorded.getTime().until(tx.getTransaction().getTime(), MINUTES) < 2))
                input.addViolation(Violation.DOUBLE_TRANSACTION.getName());
            else if (input.getAccount().getAvailableLimit() >= tx.getTransaction().getAmount()) {
                if (input.getViolationsSize() == 0) {
                    input.getAccount().doTx(tx.getTransaction().getAmount());
                    input.addTraceTransaction(tx.getTransaction());
                }

            } else
                input.addViolation(Violation.INSUFFICIENT_LIMIT.getName());
        }
        return input;
    }
}
