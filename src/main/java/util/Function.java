package util;

import model.Transaction;
import model.TxRoot;

import static java.time.temporal.ChronoUnit.MINUTES;

public final class Function {

    private Function() {}

    public static boolean isSameMerchantTx(TxRoot tx, Transaction txRecorded) {
        return txRecorded.getMerchant().equals(tx.getTransaction().getMerchant());
    }

    public static boolean isTxGreaterThanXmin(TxRoot tx, Transaction txRecorded, int min) {
        return txRecorded.getTime().until(tx.getTransaction().getTime(), MINUTES) < min;
    }

    public static boolean isEqualsAmount(TxRoot tx, Transaction txRecorded) {
        return txRecorded.getAmount().equals(tx.getTransaction().getAmount());
    }
}
