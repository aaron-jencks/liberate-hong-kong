package company.Entity.Abstract;

import company.Entity.Abstract.AManager;
import company.Entity.Interface.IHRManager;
import company.Entity.Interface.IEmployee;
import company.Entity.Interface.IPerson;

public abstract class AHRManager extends AManager implements IHRManager {

    @Override
    public IEmployee Hire(IPerson p)
    {
        // TODO
        return null;
    }
}
