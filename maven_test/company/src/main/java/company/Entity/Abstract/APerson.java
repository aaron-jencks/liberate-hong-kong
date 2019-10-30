package company.Entity.Abstract;

import company.Entity.Interface.IPerson;
import company.Entity.Interface.ISaveable;

public abstract class APerson implements IPerson , ISaveable {

    protected String firstName;
    protected String lastName;
    protected String email;


}
