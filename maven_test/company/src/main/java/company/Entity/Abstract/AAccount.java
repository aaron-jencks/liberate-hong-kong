package company.Entity.Abstract;

import company.Entity.Interface.IAccount;
import company.Entity.Interface.ISaveable;

public abstract class AAccount implements IAccount, ISaveable {

    protected long amount;
    protected long accountNumber;
}
