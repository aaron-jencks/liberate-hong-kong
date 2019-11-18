package company.Entity;

import java.math.BigDecimal;
import java.util.UUID;

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

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public SQLAccount id(UUID id) {
        this.id = id;
        return this;
    }

    public SQLAccount amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }
    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", amount='" + getAmount() + "'" +
            "}";
    }


}
