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
    protected ArrayList<IMenuItem> items = new ArrayList<>();
    protected ArrayList<IMenuItem> available = new ArrayList<>();

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

    @Override
    public void display()
    {
        available = new ArrayList<>(items.size());
        for(IMenuItem i : items)
            if(i.is_available())
                available.add(i);

        System.out.print(get_display_string());
    }

    @Override
    public IMenuItem prompt()
    {
        display();

        Scanner sc = new Scanner(System.in);
        Integer item = 0;
        try
        {
            // Normally I just use a lambda expression here, but since I need the length of the list
            // I have to use an external class.
            // But, (T v) -> {expression;} is also okay.
            item = UIUtil.get_input(sc, item, prompt, new MenuItemValidator(available.size() + 1));
        }
        catch(Exception e)
        {
            System.err.println("How the hell did you get here???");
        }

        return available.get(item - 1);
    }
}