package company.Entity;

import company.Entity.Abstract.AVault;

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
