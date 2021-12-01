package handler;

import model.Record;
import model.TxRoot;
import pipeline.Handler;

import static container.RegexContainer.isTxMatches;
import static container.Serializer.fromJson;
import static model.Violation.DOUBLE_TRANSACTION;
import static model.Violation.INSUFFICIENT_LIMIT;
import static util.Function.*;

/***
 * Handler performing a transaction
 */
public final class PerformTxHandler implements Handler<Record, Record> {

    @Override
    public Record process(Record input) {
        if (input.getAccount() != null && isTxMatches(input)) {
            var tx = fromJson(input.getInput(), TxRoot.class);

            if (input.getTxTraces()
                    .stream()
                    .anyMatch(txRecorded ->
                            isSameMerchantTx(tx, txRecorded) &&
                                    isEqualsAmount(tx, txRecorded) &&
                                        isTxGreaterThanXmin(tx, txRecorded, 2)))
                input.addViolation(DOUBLE_TRANSACTION.getName());
            else if (input.getAccount().getAvailableLimit() >= tx.getTransaction().getAmount()) {
                if (input.getViolationsSize() == 0) {
                    input.getAccount().doTx(tx.getTransaction().getAmount());
                    input.addTraceTransaction(tx.getTransaction());
                }

            } else
                input.addViolation(INSUFFICIENT_LIMIT.getName());
        }
        return input;
    }


}
