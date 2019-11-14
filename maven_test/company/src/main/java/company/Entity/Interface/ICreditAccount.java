package company.Entity.Interface;

public interface ICreditAccount extends IAccount{
    public double getInterestRate();
    public void setInterestRate(double rate);

    public long payDebt(long amount);

    public void accrueInterest();
}
