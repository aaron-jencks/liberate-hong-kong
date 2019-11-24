package company.Entity.Interface;

public interface ICreditAccount extends IAccount{
    public double getInterestRate();
    public void setInterestRate(double rate);

    /**
     * Pays on the debt for this account
     * @param amount amount to pay on this debt
     * @return Returns difference between the amount and the total amount on the debt, or 0 if the debt is paid off
     */
    public long payDebt(long amount);

    /**
     * Multiplies the amount on this account by the interest rate and then adds it to it.
     */
    public void accrueInterest();
}
