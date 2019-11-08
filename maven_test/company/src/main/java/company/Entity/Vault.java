package company.Entity;

import java.util.UUID;

import company.Entity.Abstract.AVault;
import company.Entity.Interface.ICustomer;

public class Vault extends AVault {

    private static Vault vaultInstance = null;

    /**
     * Do not make this public
     */
    private Vault(){
        super();
    }

    /**
     * Singleton Vault
     */
    public static Vault getInstance(){
        if(vaultInstance == null){
            vaultInstance = new Vault();
        }
        return vaultInstance;
    }
}
