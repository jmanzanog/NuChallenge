package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class AccountRoot {
    @SerializedName("account")
    @Expose
    private Account account;

    public Account getAccount() {
        return account;
    }

}
