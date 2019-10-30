package Entity.Interface;

import Entity.Interface.IEmployee;

public interface IMenuItem {
    public boolean is_available(IEmployee employee);
    public void Activate(IEmployee employee);
}
