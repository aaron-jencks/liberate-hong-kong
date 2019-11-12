package company.Entity.Interface;

import java.util.UUID;

public interface IAccount extends ISaveable {
    public long getAmount();
    public UUID setAmount(long amount);
}
