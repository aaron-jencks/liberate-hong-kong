package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.BankAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Interface.IBankAccount;
import company.Entity.Interface.ISaveable;

public abstract class ABankAccount extends AAccount implements IBankAccount {

    public static BankAccount load(UUID id){
        Object o = ISaveable.load(BankAccount.class, id);
        return (BankAccount)o;
    }
}
