package company.Entity.Interface;

import java.util.UUID;

public interface ICustomer {

    /**
     * Adds a an account to this person's accounts list
     * @param accountId
     * @return
     */
    public UUID addAccount(UUID accountId);

    /**
     * Removes the given account from this person's accounts list
     * @param accountId
     * @return
     */
    public UUID removeAccount(UUID accountId);

    
}