package company.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import company.Controller.AccountController;
import company.Entity.Enum.AccountType;

public class SQLAccount {
    private UUID id;
    private BigDecimal amount;
    private AccountType type;


    public SQLAccount() {
    }

    public SQLAccount(UUID id, BigDecimal amount, AccountType type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
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
        AccountController.getInstance().updateAccount(this);
    }

    public AccountType getType() {
        return this.type;
    }

    public void setType(AccountType type) {
        this.type = type;
        AccountController.getInstance().updateAccount(this);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
