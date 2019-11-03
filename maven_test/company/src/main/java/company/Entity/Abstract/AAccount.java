package company.Entity.Abstract;

import company.Entity.Interface.IAccount;

public abstract class AAccount extends ASaveable implements IAccount
{

    protected long amount;
    protected String accountNumber;

    public AAccount(){
        this.accountNumber = null;
        this.amount = 0l;
    }

    public long getAmount()
    {
        return amount;
    }

    public AAccount setAmount(long amount)
    {
        this.amount = amount;
        return this;
    }

    public String getAccountNumber()
    {
        return accountNumber;
    }

    public AAccount setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
        return this;
    }
}
