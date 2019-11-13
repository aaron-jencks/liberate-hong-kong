package UI.menus.AccountMenu.items;

import java.util.Scanner;

import UI.AMenu;
import UI.AMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;

public class AccrueInterestItem extends AMenuItem {

    public AccrueInterestItem(ITermController parent) {
        super(parent);
        // TODO Auto-generated constructor stub
    }

    @Override
    public AMenu activate() {
        Scanner sc = new Scanner(System.in);
        String confirm = "";

        String padding = "";

        try {
            confirm = UIUtil.get_input(sc, confirm, padding + "Are you sure that you want to accrue interest on ALL credit accounts?(y/[N])", (String s) -> {
                return s.length() == 0 || s.toUpperCase().charAt(0) == 'Y' || s.toUpperCase().charAt(0) == 'N';
            });

            if(confirm.length() > 0)
            {
                char temp_confirm = confirm.toUpperCase().charAt(0);
                if(temp_confirm == 'Y')
                {
                    // TODO accrue the interest
                }
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