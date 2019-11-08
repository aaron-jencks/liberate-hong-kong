package company.Entity.Interface;

import java.util.ArrayList;
import java.util.UUID;

public interface ICustomer extends IPerson {
    public ICustomer addAccount(UUID accountId);
    public ICustomer removeAccount(UUID accountId);
    public ICustomer setAccountIds(ArrayList<UUID> accountIds);
    public ArrayList<UUID> getAccountIds();
}
