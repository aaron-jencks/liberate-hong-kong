package company.Entity;

import java.util.ArrayList;
import java.util.UUID;

import company.Controller.CustomerController;

public class SQLCustomer extends SQLPerson{
    private ArrayList<UUID> accounts = new ArrayList<>();

    public SQLCustomer() {
        super();
    }

    public SQLCustomer(UUID id, String firstName, String lastName, ArrayList<UUID> accounts){
        super(id, firstName, lastName);
        this.accounts = accounts;
    }

    public SQLCustomer(UUID id, ArrayList<UUID> accounts, SQLPerson person){
        super(id, person.getFirstName(), person.getLastName());
        this.accounts = accounts;
    }

    public SQLCustomer(UUID id, String firstName, String lastName){
        super(id, firstName, lastName);
        accounts = new ArrayList<>();
    }

    public SQLCustomer(ArrayList<UUID> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<UUID> getAccounts() {
        return this.accounts;
    }

    public void addAccount(SQLAccount account){
        this.addAccount(account.getId());
    }

    public void addAccount(UUID accountId){
        this.accounts.add(accountId);
    }

    public String getAccountsString(){
        if(accounts.isEmpty()){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (UUID uuid : accounts) {
            sb.append(uuid.toString());
            sb.append(',');
        }
        String ret = sb.toString();
        ret = ret.substring(0, ret.length() - 1);
        return ret;
    }

    public void setAccounts(ArrayList<UUID> accounts) {
        this.accounts = accounts;
        CustomerController.getInstance().updateCustomer(this);
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
        CustomerController.getInstance().updateCustomer(this);
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
        CustomerController.getInstance().updateCustomer(this);
    }

    @Override
    public String toString() {
        return "{" +
            " accounts='" + getAccounts() + "'" +
            "}";
    }

}