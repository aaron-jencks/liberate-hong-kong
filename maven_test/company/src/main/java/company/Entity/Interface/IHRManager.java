package company.Entity.Interface;

import company.Entity.Interface.IPerson;

public interface IHRManager extends IManager{

    /**
     * Hires a new person
     * @param p
     * @return
     */
    public long Hire(IPerson p);
}
