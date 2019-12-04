package UI.menus.GreeterMenu.items;

import java.util.Scanner;

import UI.AMenu;
import UI.AMenuItem;
import UI.AnsiUtil;
import UI.UIUtil;

public class ToasterItem extends AMenuItem {

    @Override
    public AMenu activate()
    {
        Scanner sc = new Scanner(System.in);

        // Display the message in a box
        AnsiUtil.display_window(false, 
                                UIUtil.create_box_string("Hello World!\nThis is Aaron" + 
                                    "\nPress any key to continue."));

        // Wait for the user to press a key
        String prompt = "";

        try {
            UIUtil.get_input(sc, prompt, "", (String s) -> { return true; });
        }
        catch(Exception e) {
            System.err.println("How in the world?");
        }

        return null;
    }

    @Override
    public String toString() { return "Toast me"; }
}