package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public final class Account implements Serializable {

    @SerializedName("active-card")
    @Expose
    private Boolean activeCard;
    @SerializedName("available-limit")
    @Expose
    private Integer availableLimit;

    public Boolean getActiveCard() {
        return activeCard;
    }

    public Integer getAvailableLimit() {
        return availableLimit;
    }

    public void doTx(Integer value) {
        this.availableLimit = this.availableLimit - value;
    }
}
