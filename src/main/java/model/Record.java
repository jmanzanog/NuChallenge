package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Record implements Serializable {
    private Account account;
    private final List<String> violations;
    private transient List<Transaction> txTraces;
    private final transient String input;

    public Record(String input) {
        this.input = input;
        this.violations = new ArrayList<>();
        this.txTraces = new ArrayList<>();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void addViolation(String violation) {
        this.violations.add(violation);
    }

    public String getInput() {
        return input;
    }

    public int getViolationsSize() {
        return violations.size();
    }

    public void addTraceTransaction(Transaction transaction) {
        this.txTraces.add(transaction);
    }

    public List<Transaction> getTxTraces() {
        return txTraces;
    }

    public void setTxTraces(List<Transaction> txTraces) {
        this.txTraces = txTraces;
    }

}
