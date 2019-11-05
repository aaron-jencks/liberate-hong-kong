package company.Entity.Interface;

public interface IPerson extends ISaveable {
    public String getFirstName();
    public IPerson setFirstName(String firstName);
    public String getLastName();
    public IPerson setLastName(String lastName);
}
