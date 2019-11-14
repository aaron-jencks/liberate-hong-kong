package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.CreditAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Interface.ICreditAccount;
import company.Entity.Interface.ISaveable;

public abstract class ACreditAccount extends AAccount implements ICreditAccount {
    private double interestRate;

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

    public void accrueInterest()
    {
        amount *= interestRate;
    }
}
