package company.Entity.Interface;

import java.util.UUID;

import company.Entity.Teller;

public interface ITeller extends IEmployee{
    public static Teller load(UUID id){
        Object o = ISaveable.load(Teller.class, id);
        return (Teller)o;
    }
}
