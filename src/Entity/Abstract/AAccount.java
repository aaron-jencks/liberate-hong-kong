package Entity.Abstract;

import Entity.Interface.IAccount;

public abstract class AAccount extends ASaveable implements IAccount
{

    protected long amount;
    protected long accountNumber;

    public long getAmount()
    {
        return amount;
    }

    public AAccount setAmount(long amount)
    {
        this.amount = amount;
        return this;
    }

    public long getAccountNumber()
    {
        return accountNumber;
    }

    public AAccount setAccountNumber(long accountNumber)
    {
        this.accountNumber = accountNumber;
        return this;
    }
}
