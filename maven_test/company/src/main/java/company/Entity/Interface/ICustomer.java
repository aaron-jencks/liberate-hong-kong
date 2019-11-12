package company.Entity.Interface;

import java.util.ArrayList;
import java.util.UUID;

public interface ICustomer extends IPerson {
    public UUID addAccount(UUID accountId);
    public UUID removeAccount(UUID accountId);
    public UUID setAccountIds(ArrayList<UUID> accountIds);
    public ArrayList<UUID> getAccountIds();
}
