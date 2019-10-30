package Entity.Abstract;

import Entity.Interface.ICustomer;
import Entity.Interface.ISaveable;

import java.util.ArrayList;

public abstract class ACustomer extends APerson implements ICustomer , ISaveable {
    protected ArrayList<String> accountIds;

    public ArrayList<String> getAccountIds()
    {
        return accountIds;
    }

    public void setAccountIds(ArrayList<String> accountIds)
    {
        this.accountIds = accountIds;
    }
}
