package UI;

import java.util.ArrayList;
import java.util.Scanner;

import UI.IMenu;
import UI.IMenuItem;
import UI.MenuItemValidator;
import UI.UIUtil;
import UI.controller.ITermController;

public abstract class AMenu implements IMenu {
    protected ITermController parent;
    protected String title = new String();
    protected String prompt = "What would you like to do? ";
    protected ArrayList<IMenuItem> items = new ArrayList<IMenuItem>();
    protected ArrayList<IMenuItem> available = new ArrayList<IMenuItem>();
    public boolean is_valid = false;

    public AMenu(ITermController parent)
    {
        super();
        this.parent = parent;
    }

    @Override
    public String get_display_string()
    {
        return UIUtil.create_menu_string(title, true, available, "");
    }

    protected void populate_availabled()
    {
        available = new ArrayList<>(items.size());
        for(IMenuItem i : items)
            if(i.is_available())
                available.add(i);

        is_valid = false;
    }

    @Override
    public void display()
    {
        populate_availabled();

        System.out.print(get_display_string());

        is_valid = true;
    }

    @Override
    public void invalidate()
    {
        is_valid = false;
    }

    @Override
    public IMenuItem prompt()
    {
        if(!is_valid)
            display();

        Scanner sc = new Scanner(System.in);
        Integer item = 0;
        try
        {
            // Normally I just use a lambda expression here, but since I need the length of the list
            // I have to use an external class.
            // But, (T v) -> {expression;} is also okay.
            item = UIUtil.get_input(sc, item, prompt, new MenuItemValidator(available.size()));
        }
        catch(Exception e)
        {
            System.err.println("How the hell did you get here???");
        }

        return available.get(item - 1);
    }
}