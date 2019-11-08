package company.Entity.Interface;

public interface IAccount extends ISaveable {
    public long getAmount();
    public IAccount setAmount(long amount);
}
