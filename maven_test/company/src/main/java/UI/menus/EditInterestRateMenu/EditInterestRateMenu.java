package UI.menus.EditInterestRateMenu;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.AccountController;
import company.Entity.Account;
import company.exceptions.BankLockedException;

public class EditInterestRateMenu extends AMenu {
    private String accountNumber = new String();
    private Double depositAmount = 0.0;
    private String accept = new String();

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
             s = "Please enter the account number you would like to adjust: ";
         } else if (depositAmount.equals(0.0)) {
             s = "Please enter a new percentage to use as the interest rate: ";
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
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            accountNumber = UIUtil.get_input(sc, accountNumber, "", (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            UUID id = UUID.fromString(accountNumber);
        } catch (IllegalArgumentException e) {
            // accountNumber = new String();
            // invalidate();
            // return null;
            toast("Invalid account number.");
            return new ExitItem();
        }

        Account account;

        try {
            account = AccountController.getInstance().getAccount(accountNumber);
        }
        catch (BankLockedException e) {
            toast("Cannot edit the interest rate because the bank is locked.");
            return new ExitItem();
        }

        BigDecimal startAmount = account.getInterestRate();
        get_display_string();

        try {
            AnsiUtil.append_display_string(get_x_coord(), prompt);
            depositAmount = UIUtil.get_input(sc, depositAmount, prompt + " Current account rate: " + startAmount + ". \n Enter Interest Rate: ", (Double s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        try {
            account.setInterestRate(new BigDecimal(depositAmount));
        }
        catch (BankLockedException e) {
            toast("Cannot edit the interest rate because the bank is locked.");
            return new ExitItem();
        }

        toast("Interest rate changed.");

        return new ExitItem();
    }

}