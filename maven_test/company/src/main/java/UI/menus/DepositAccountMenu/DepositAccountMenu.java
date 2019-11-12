package UI.menus.DepositAccountMenu;

import java.util.Scanner;
import java.util.UUID;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Entity.BankAccount;
import company.Entity.Abstract.ABankAccount;
import company.Entity.Abstract.ASaveable;
import company.Entity.Interface.ISaveable;

public class DepositAccountMenu extends AMenu {
    private String accountNumber;
    private String depositAmount;
    private String accept;
    private String totalAmount;

    public DepositAccountMenu(ITermController parent) {
        super(parent);
    }

    @Override
    public String get_display_string() {
        String result = new String();
         // Add vertical padding
         int v_pad = get_y_coord(), h_pad = get_x_coord();
         for (int i = 0; i < v_pad; i++)
             result += "\n";
 
         String new_prompt = new String();
         for (int i = 0; i < h_pad; i++)
             new_prompt += " ";
 
         String s = "";
         if (accountNumber.isEmpty()) {
             s = "Please enter the account number you would like to deposit into: ";
         } else if (depositAmount.isEmpty()) {
             s = "How much would you like to deposit today: ";
         }
 
         prompt = new_prompt + s;
 
         return result;
    }

    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);

        if (!is_valid)
            display();

        try {
            accountNumber = UIUtil.get_input(sc, accountNumber, prompt, (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        BankAccount ba = ABankAccount.load(UUID.fromString(accountNumber));

        get_display_string();

        try {
            depositAmount = UIUtil.get_input(sc, depositAmount, prompt, (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        

        try {
            accept = UIUtil.get_input(sc, accept, prompt + " Account created. Account id = " + accountNumber
                    + "\n Press any key to return to the account menu.", (String s) -> {
                        return true;
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExitItem(this.parent);
    }

}