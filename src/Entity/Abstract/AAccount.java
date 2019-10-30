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

    public void setAmount(long amount)
    {
        this.amount = amount;
    }

    public long getAccountNumber()
    {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber)
    {
        this.accountNumber = accountNumber;
    }
}
