package main.java.UI.menus.LoginMenu;

import java.util.Scanner;

import main.java.UI.AMenu;
import main.java.UI.IMenuItem;
import main.java.UI.controller.ITermController;
import main.java.UI.menus.LoginMenu.items.MainMenuItem;
import main.java.UI.UIUtil;

public class LoginMenu extends AMenu {
    private String username = new String();
    private String password = new String();

    public LoginMenu(ITermController parent) { super(parent); }

    @Override
    public int get_y_coord()
    {
        return (parent.get_term_height() - 2) >> 1;
    }

    @Override
    public String get_display_string()
    {
        String result = new String();

        // Add vertical padding
        int v_pad = get_y_coord(), h_pad = get_x_coord();
        for(int i = 0; i < v_pad; i++)
            result += "\n";

        String new_prompt = new String();
        for(int i = 0; i < h_pad; i++)
            new_prompt += " ";
        prompt = new_prompt + ((username.isEmpty()) ? "Username: " : "Password: ");

        return result;
    }

    @Override
    public IMenuItem prompt()
    {
        Scanner sc = new Scanner(System.in);

        if(!is_valid)
            display();

        try { username = UIUtil.get_input(sc, username, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        get_display_string();

        try { password = UIUtil.get_input(sc, password, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        // TODO check existence

        return new MainMenuItem(parent);
    }
}