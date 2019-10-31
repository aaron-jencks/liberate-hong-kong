package UI;

import java.util.ArrayList;
import java.util.Scanner;

import UI.IMenu;
import UI.IMenuItem;
import UI.MenuItemValidator;
import UI.UIUtil;
import UI.controller.ITermController;

public abstract class AMenu implements IMenu {

    protected static Scanner inputScanner = null;

    protected ITermController parent;
    protected String title = new String();
    protected String prompt = "What would you like to do? ";
    protected ArrayList<IMenuItem> items = new ArrayList<IMenuItem>();
    protected ArrayList<IMenuItem> available = new ArrayList<IMenuItem>();
    private boolean is_availabled = false;
    public boolean is_valid = false;
    public boolean is_centered = true;

    public AMenu(ITermController parent)
    {
        super();
        this.parent = parent;
    }

    /**
     * Determines the column that the menu box for this menu starts in
     * @return Returns the index of the column that this menu starts in.
     */
    public int get_x_coord()
    {
        int menu_width = 0, diff;

        for(String s : UIUtil.create_menu_string(title, true, available, "").split("\n"))
            if(s.length() > menu_width)
                menu_width = s.length();

        diff = parent.get_term_width() - menu_width;
        if(diff < 0)
            return 0;
        else
            return diff >> 1;
    }

    public int get_y_coord()
    {
        if(!is_availabled)
            populate_availabled();

        return (parent.get_term_height() - available.size() - 3) >> 1;
    }

    @Override
    public String get_display_string()
    {
        if(is_centered)
        {
            String result = new String();
            String[] temp = UIUtil.create_menu_string(title, true, available, "").split("\n");

            // Add vertical padding
            int v_pad = get_y_coord(), h_pad = get_x_coord();
            for(int i = 0; i < v_pad; i++)
                result += "\n";

            for(String str : temp)
                if(!str.isEmpty())
                    result += UIUtil.pad_string(str, 
                                                parent.get_term_width(), 
                                                AlignmentType.center) + '\n';

            String new_prompt = new String();
            for(int i = 0; i < h_pad; i++)
                new_prompt += " ";
            prompt = new_prompt + prompt;

            return result;
        }
        else
            return UIUtil.create_menu_string(title, true, available, "");
    }

    protected void populate_availabled()
    {
        available = new ArrayList<>(items.size());
        for(IMenuItem i : items)
            if(i.is_available())
                available.add(i);

        is_valid = false;
        is_availabled = true;
    }

    @Override
    public void display()
    {
        if(!is_availabled)
            populate_availabled();

        System.out.print(get_display_string());

        is_valid = true;
    }

    @Override
    public void invalidate()
    {
        is_valid = false;
        is_availabled = false;
    }

    @Override
    public IMenuItem prompt()
    {
        if(!is_valid)
            display();

        if(AMenu.inputScanner == null)
            AMenu.inputScanner = new Scanner(System.in);
        Integer item = 0;
        try
        {
            // Normally I just use a lambda expression here, but since I need the length of the list
            // I have to use an external class.
            // But, (T v) -> {expression;} is also okay.
            item = UIUtil.get_input(AMenu.inputScanner, item, prompt, new MenuItemValidator(available.size()));
        }
        catch(Exception e)
        {
            System.err.println("How the hell did you get here???");
        }

        return available.get(item - 1);
    }
}