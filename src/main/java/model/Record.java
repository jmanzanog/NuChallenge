package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public final class Record implements Serializable {
    private final Account account;
    private final List<String> violations;
    private transient List<Transaction> txTraces;
    private final transient String input;

    public Record(String input) {
        this.input = input;
        this.violations = new ArrayList<>();
        this.txTraces = new ArrayList<>();
        this.account = null;
    }

    public Record(Account account, List<String> violations, List<Transaction> txTraces, String input) {
        this.account = account;
        this.violations = violations;
        this.txTraces = txTraces;
        this.input = input;
    }

    public Account getAccount() {
        return account;
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

    public List<String> getViolations() {
        return violations;
    }

}
