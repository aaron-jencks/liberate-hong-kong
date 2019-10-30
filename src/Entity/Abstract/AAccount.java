package Entity.Abstract;

import Entity.Interface.IAccount;
import Entity.Interface.ISaveable;

public abstract class AAccount implements IAccount, ISaveable {

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
