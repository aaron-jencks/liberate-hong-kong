package UI;

import UI.IMenuItem;
import UI.controller.ITermController;

public abstract class AMenuItem implements IMenuItem {

    protected ITermController parent;

    public AMenuItem(ITermController parent)
    {
        this.parent = parent;
    }
    
    @Override
    public boolean is_available() { return true; }

    @Override
    public String toString()
    {
        return "No name was supplied.";
    }

}