package model;

public enum Violation {
    INSUFFICIENT_LIMIT("insufficient-limit"),
    ACCOUNT_ALREADY_INITIALIZED("account-already-initialized"),
    ACCOUNT_NOT_INITIALIZED("account-not-initialized"),
    CARD_NOT_ACTIVE("card-not-active"),
    HIGH_FREQUENCY_SMALL_INTERVAL("high-frequency-small-interval"),
    DOUBLE_TRANSACTION("doubled-transaction");


    private final String name;

    Violation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
