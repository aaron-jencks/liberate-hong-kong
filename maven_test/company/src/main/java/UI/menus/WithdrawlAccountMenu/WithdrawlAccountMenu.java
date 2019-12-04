package UI.menus.WithdrawlAccountMenu;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Scanner;

import UI.AMenu;
import UI.AnsiUtil;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.AccountController;
import company.Entity.Account;

public class WithdrawlAccountMenu extends AMenu {
    private String accountNumber = new String();
    private String withdrawlAmount = new String();
    private String accept = new String();
    private String totalAmount = new String();

    @Override
    public String get_display_string() {
         String result = new String();
 
         String s = "";
         if (accountNumber.isEmpty()) {
             s = "Please enter the account number you would like to withdrawl from: ";
         } else if (withdrawlAmount.isEmpty()) {
             s = "How much would you like to withdrawl today: ";
         }
 
         prompt = s;
 
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
        //try to get the account
        Account a = AccountController.getInstance().getAccount(accountNumber);

        if(a == null){
            toast("Account not found. " + totalAmount);
            return new ExitItem();
        }

        BigDecimal startAmount = a.getAmount();
        get_display_string();

        try {
            AnsiUtil.append_display_string(get_x_coord(), " Current account balance: " + startAmount.toString() + ". \n Enter withdraw amount: ");
            withdrawlAmount = UIUtil.get_input(sc, withdrawlAmount, "", (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        try {
            AccountController.getInstance().withdrawl(a, new BigDecimal(withdrawlAmount));
        } catch (InvalidParameterException e) {
            toast("Insufficent funds.");
            return new ExitItem();
        }

        totalAmount = a.getAmount().toString();

        toast("Amount withdrawn. Total amount on account = " + totalAmount);

        return new ExitItem();
    }

}