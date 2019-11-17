package company.Entity.Abstract;

import company.Entity.Interface.IEmployee;

public abstract class ATeller extends AEmployee implements IEmployee{

    public ATeller(APerson person){
        super(person);
        this.setObjectId(person.getObjectId());
        person.delete();
    }
}