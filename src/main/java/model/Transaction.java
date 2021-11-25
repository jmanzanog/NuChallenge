package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDateTime;

public final class Transaction {

    @SerializedName("merchant")
    @Expose
    private String merchant;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("time")
    @Expose
    private LocalDateTime time;

    public String getMerchant() {
        return merchant;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDateTime getTime() {
        return time;
    }

}
