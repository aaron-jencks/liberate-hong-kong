package UI;

import java.util.ArrayList;
import java.util.Scanner;

import UI.IMenu;
import UI.IMenuItem;
import UI.MenuItemValidator;
import UI.UIUtil;

public abstract class AMenu implements IMenu {
    private String title = new String();
    private String prompt = new String();
    private ArrayList<IMenuItem> items = new ArrayList<>();
    private ArrayList<IMenuItem> available = new ArrayList<>();

    public void display()
    {
        available = new ArrayList<>(items.size());
        for(IMenuItem i : items)
            if(i.is_available())
                available.add(i);

        System.out.print(UIUtil.create_menu_string(title, true, available, ""));
    }

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