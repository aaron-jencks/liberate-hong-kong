package company.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import company.Controller.AccountController;

public class SQLAccount {
    private UUID id;
    private BigDecimal amount;


    public SQLAccount() {
    }

    public SQLAccount(UUID id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public UUID getId() {
        return this.id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        BigDecimal rounded = amount.setScale(2, RoundingMode.HALF_EVEN);
        this.amount = rounded;
        AccountController ac = AccountController.getInstance();
        ac.updateAccount(this);
    }
    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }


}
