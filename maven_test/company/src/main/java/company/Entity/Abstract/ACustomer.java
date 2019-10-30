package company.Entity.Abstract;

import company.Entity.Interface.ICustomer;
import company.Entity.Interface.ISaveable;

import java.util.ArrayList;

public abstract class ACustomer extends APerson implements ICustomer , ISaveable {
    protected ArrayList<String> accountIds;

}
