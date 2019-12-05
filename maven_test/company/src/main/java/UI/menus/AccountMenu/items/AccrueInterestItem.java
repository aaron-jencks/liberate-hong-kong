package UI.menus.AccountMenu.items;

import java.util.Scanner;

import UI.AMenu;
import UI.AMenuItem;
import UI.AnsiUtil;
import UI.UIUtil;
import company.Controller.AccountController;
import company.exceptions.BankLockedException;

public class AccrueInterestItem extends AMenuItem {

    @Override
    public AMenu activate() {
        Scanner sc = new Scanner(System.in);
        String confirm = "";

        String padding = "";

        try {
            // Display the message in a box
            AnsiUtil.display_window(false, 
                UIUtil.create_box_string("Are you sure that you want to accrue interest on ALL credit accounts?(y/[N])" + 
                                         "\nPress ENTER to continue."));
            AnsiUtil.center_cursor_horiz();
            confirm = UIUtil.get_input(sc, confirm, "", (String s) -> {
                return s.length() == 0 || s.toUpperCase().charAt(0) == 'Y' || s.toUpperCase().charAt(0) == 'N';
            });

            if(confirm.length() > 0)
            {
                char temp_confirm = confirm.toUpperCase().charAt(0);
                if(temp_confirm == 'Y')
                    AccountController.getInstance().accrueInterest();
            }
        }
        catch(BankLockedException e)
        {
            // Display the message in a box
            AnsiUtil.display_window(false, 
                UIUtil.create_box_string("Cannot accrue interest, the bank is locked." + 
                                         "\nPress ENTER to continue."));

            // Wait for the user to press a key
            String prompt = "";

            try {
                UIUtil.get_input(sc, prompt, "", (String s) -> { return true; });
            }
            catch(Exception d) {
                System.err.println("How in the world?");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String toString() {
        return "Accrue Interest";
    }

}