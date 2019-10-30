package company.Entity.Abstract;

import company.Entity.Abstract.AEmployee;
import company.Entity.Interface.IManager;
import company.Entity.Interface.IEmployee;

public abstract class AManager extends AEmployee implements IManager {

    @Override
    public void Fire(IEmployee e)
    {
        // TODO
    }
}
