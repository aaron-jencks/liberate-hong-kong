package company.Entity.Abstract;

import company.Entity.Interface.IAccount;

public abstract class AAccount extends ASaveable implements IAccount
{
    protected long amount;

    public AAccount(){
        super();
        this.amount = 0l;
    }

    @Override
    public long getAmount()
    {
        return amount;
    }

    @Override
    public IAccount setAmount(long amount)
    {
        this.amount = amount;
        this.save();
        return this;
    }
}
