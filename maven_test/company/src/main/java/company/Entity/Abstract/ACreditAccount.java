package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.CreditAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Interface.ICreditAccount;
import company.Entity.Interface.ISaveable;

public abstract class ACreditAccount extends AAccount implements ICreditAccount {
    public static CreditAccount load(UUID id){
        Object o = ISaveable.load(CreditAccount.class, id);
        return (CreditAccount)o;
    }
}
