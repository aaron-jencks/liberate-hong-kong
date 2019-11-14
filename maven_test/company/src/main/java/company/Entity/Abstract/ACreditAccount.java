package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.CreditAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Interface.ICreditAccount;
import company.Entity.Interface.ISaveable;

public abstract class ACreditAccount extends AAccount implements ICreditAccount {
    private double interestRate = 0.05;

    public static CreditAccount load(UUID id){
        Object o = ISaveable.load(CreditAccount.class, id);
        return (CreditAccount)o;
    }

    public double getInterestRate()
    {
        return interestRate;
    }

    public void setInterestRate(double rate)
    {
        interestRate = rate;
    }

    public long payDebt(long amount)
    {
        if(amount > this.amount)
        {
            this.amount = 0;
            return amount - this.amount;
        }
        else
        {
            this.amount -= amount;
            return 0;
        }
    }

    public void accrueInterest()
    {
        amount += amount * interestRate;
    }
}
