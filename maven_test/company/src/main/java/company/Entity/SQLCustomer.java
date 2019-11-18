package company.Entity;

import java.util.ArrayList;
import java.util.UUID;

public class SQLCustomer extends SQLPerson{
    private ArrayList<UUID> accounts = new ArrayList<>();

    public SQLCustomer() {
        super();
    }

    public SQLCustomer(UUID id, String firstName, String lastName){
        super(id, firstName, lastName);
    }

    public SQLCustomer(ArrayList<UUID> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<UUID> getAccounts() {
        return this.accounts;
    }

    public void setAccounts(ArrayList<UUID> accounts) {
        this.accounts = accounts;
    }

    public SQLCustomer accounts(ArrayList<UUID> accounts) {
        this.accounts = accounts;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " accounts='" + getAccounts() + "'" +
            "}";
    }

}