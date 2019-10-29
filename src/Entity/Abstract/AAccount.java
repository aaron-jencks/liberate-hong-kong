package Entity.Abstract;

import Entity.Interface.IAccount;
import Entity.Interface.ISaveable;

public abstract class AAccount implements IAccount, ISaveable {

    protected long amount;
    protected long accountNumber;
}
