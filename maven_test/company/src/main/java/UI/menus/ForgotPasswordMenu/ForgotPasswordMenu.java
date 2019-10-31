package UI.menus.ForgotPasswordMenu;

import java.util.Scanner;

import UI.menus.LoginMenu.LoginMenu;
import UI.IMenuItem;
import UI.controller.ITermController;
import UI.menus.LoginMenu.items.MainMenuItem;
import UI.UIUtil;

public class ForgotPasswordMenu extends LoginMenu {
    protected String security_question = new String();
    protected String security_answer = new String();
    protected String confirm_password = new String();

    public ForgotPasswordMenu(ITermController parent) { super(parent); }

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
        prompt = new_prompt + ((username.isEmpty()) ? "Username: " : 
                               ("Password: ");

        return result;
    }

    @Override
    public IMenuItem prompt()
    {
        if(ForgotPasswordMenu.inputScanner == null)
            ForgotPasswordMenu.inputScanner = new Scanner(System.in);

        if(!is_valid)
            display();

        try { username = UIUtil.get_input(ForgotPasswordMenu.inputScanner, username, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        get_display_string();

        try { password = UIUtil.get_input(ForgotPasswordMenu.inputScanner, password, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        // TODO check existence

        return new MainMenuItem(parent);
    }
}