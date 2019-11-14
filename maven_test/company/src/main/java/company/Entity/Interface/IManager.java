package company.Entity.Interface;

import company.Entity.Interface.IEmployee;

public interface IManager extends IEmployee{
    /**
     * Fires the given employee
     * @param e
     */
    public void Fire(IEmployee e);
}
