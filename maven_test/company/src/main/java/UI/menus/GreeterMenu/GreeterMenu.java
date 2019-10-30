package UI.menus.GreeterMenu;

import UI.AMenu;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.menus.GreeterMenu.items.LoginItem;
import UI.global_menu_items.ExitItem;
import UI.AlignmentType;

public class GreeterMenu extends AMenu {
    public GreeterMenu(ITermController parent)
    {
        super(parent);
        title = "Welcome to the Hong Kong Liberation Banking System";
        items.add(new LoginItem(this.parent));
        items.add(new ExitItem(this.parent));
    }

    /**
     * Determines the column that the menu box for this menu starts in
     * @return Returns the index of the column that this menu starts in.
     */
    public int get_x_coord()
    {
        int menu_width = 0, diff;

        for(String s : super.get_display_string().split("\n"))
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
        return (parent.get_term_height() - 5) / 2;
    }

    @Override
    public String get_display_string()
    {
        String result = new String();
        String[] temp = super.get_display_string().split("\n");

        // Add vertical padding
        int v_pad = get_y_coord();
        for(int i = 0; i < v_pad; i++)
            result += "\n";

        for(String str : temp)
            if(!str.isEmpty())
                result += UIUtil.pad_string(str, 
                                            parent.get_term_width(), 
                                            AlignmentType.center) + '\n';

        return result;
    }
}