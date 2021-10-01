package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class TxRoot {

    @SerializedName("transaction")
    @Expose
    private Transaction transaction;

    public Transaction getTransaction() {
        return transaction;
    }

}
