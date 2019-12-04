package UI.menus;

import UI.AMenu;
import UI.AlignmentType;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.TermController;
import UI.global_menu_items.ExitItem;

/**
 * Represents a menu that is used to display items,
 * Has the ability to page the results so that the user can flip through them as they wish.
 */
public abstract class ADisplayMenu extends AMenu {
    /**
     * Represents the current page that the display is on
     */
    protected int page = 1;

    /**
     * Represents the number of items in the iterable that was passed in
     */
    protected int item_count = 0;

    /**
     * Represents the number of pages that there are
     */
    protected int page_count = 1;

    /**
     * Represents the number of items that are displayed per page
     */
    protected int page_item_count = 0;

    /**
     * Contains a reference to the iterable that was passed in.
     */
    protected Iterable items;

    public ADisplayMenu(Iterable list)
    {
        super();
        items = list;

        item_count = 0;
        for(Object o : list) item_count++;

        if(item_count > 0)
        {
            page_count = item_count / (TermController.get_instance().get_term_height() - 3);

            page_item_count = item_count / page_count;
        }
    }

    @Override
    public String get_display_string()
    {
        // Create the header
        String result = "Page: " + page + "/" + page_count + "\n" + 
                        UIUtil.pad_string(title, TermController.get_instance().get_term_width(), 
                            AlignmentType.center);

        // Create the list for this page
        int counter = (page - 1) * page_item_count + 1;
        int printed = 0;
        int current = 0;
        for(Object o : items)
        {
            if(current++ < counter) continue;
            if(printed++ < page_item_count)
                result += UIUtil.pad_string(counter++ + ": " + o.toString(), TermController.get_instance().get_term_width(),
                                            AlignmentType.center) + "\n";
            else
                break;
        }

        prompt = "[P]rev [N]ext [E]xit : What would you like to do? ";

        return result;
    }

    @Override
    public IMenuItem prompt()
    {
        if(!is_valid)
            display();

        String resp = "";

        while(resp.toUpperCase().indexOf("E") < 0)
        {
            AnsiUtil.append_centered_display_string(prompt);
            try {
                resp = UIUtil.get_input(AMenu.inputScanner, resp, "", (String s) -> {
                    if(s.length() == 1)
                    {
                        char c = s.toUpperCase().charAt(0);
                        return s.length() == 1 && (c == 'P' || c == 'N' || c == 'E');
                    }

                    return false;
                });
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            char c = resp.toUpperCase().charAt(0);

            switch(c){
                case 'P':
                    if(page > 1) page--;
                    display();
                    break;
                case 'N':
                    if(page < page_count) page++;
                    display();
                    break;
            }
        }
        
        return new ExitItem();
    }
}