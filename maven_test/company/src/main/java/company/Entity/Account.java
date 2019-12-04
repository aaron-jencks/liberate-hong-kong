package company.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import company.Controller.AccountController;
import company.Entity.Enum.AccountType;

public class Account {
    private UUID id;
    private BigDecimal amount;
    private AccountType type;
    private BigDecimal interestRate;


    public Account() {
    }

    public Account(UUID id, BigDecimal amount, AccountType type, BigDecimal rate){
        this(id, amount, type);
        this.interestRate = rate;
    }

    public Account(UUID id, BigDecimal amount, AccountType type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    public void accrueInterest(){
        this.amount = this.amount.multiply(this.interestRate);
        AccountController.getInstance().updateAccount(this);
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

    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        AccountController.getInstance().updateAccount(this);
    }

    @Override
    public String toString() {
        // return "{" +
        //     " id='" + getId() + "'" +
        //     ", amount='" + getAmount() + "'" +
        //     ", type='" + getType() + "'" +
        //     ", interestRate='" + getInterestRate() + "'" +
        //     "}";

        return "Id: " + getId() + " Type: " + getType() + 
            " Amount: " + getAmount() + " Interest Rate: " + getInterestRate();
    }

}
