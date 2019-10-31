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
    protected int iteration = 0;

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
        prompt = new_prompt + ((iteration == 0) ? "Username: " :
                (iteration == 1) ? "Security Question: " + security_question :
                        (iteration == 2) ? "New Password: " : "Confirm Password: ");
        if(iteration++ == 3)
            iteration = 0;
            
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

        // TODO Fetch user security question
        get_display_string();
        System.out.println(prompt);

        get_display_string();
        try { security_answer = UIUtil.get_input(ForgotPasswordMenu.inputScanner, security_answer, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        // TODO check existence

        get_display_string();
        try { password = UIUtil.get_input(ForgotPasswordMenu.inputScanner, password, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        get_display_string();
        try { confirm_password = UIUtil.get_input(ForgotPasswordMenu.inputScanner, confirm_password, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        return new MainMenuItem(parent);
    }
}