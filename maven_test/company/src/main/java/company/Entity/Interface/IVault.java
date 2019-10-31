package company.Entity.Interface;

import company.Entity.Person;

public interface IVault
{
    public IEmployee getEmployee(Person p);
    public long createTeller(Person p);
    public long createLoanOfficer(Person p);
    public long createManager(Person p);
    public long createHRManager(Person p);
    public long createOwner(Person p);

}
