package Entity.Abstract;

import Entity.Interface.IPerson;
import Entity.Interface.ISaveable;

public abstract class APerson implements IPerson , ISaveable {

    protected String firstName;
    protected String lastName;
    protected String email;


}
