package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.Teller;
import company.Entity.Interface.IEmployee;

public abstract class ATeller extends AEmployee implements IEmployee{

    public static Teller load(UUID id){
        Object o = ASaveable.load(Teller.class, id);
        return (Teller)o;
    }
}