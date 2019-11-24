package UI.menus.PayCreditAccountMenu;

import java.math.BigDecimal;
import java.util.Scanner;
import java.util.UUID;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import UI.global_menu_items.ExitItem;
import company.Controller.AccountController;
import company.Entity.Account;

public class PayCreditAccountMenu extends AMenu {
    private String accountNumber = new String();
    private String depositAmount = new String();
    private String accept = new String();
    private String totalAmount = new String();

    public PayCreditAccountMenu(ITermController parent) {
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
             s = "Please enter the account number you would like to pay on: ";
         } else if (depositAmount.isEmpty()) {
             s = "How much would you like to pay today: ";
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
        try {
            UUID id = UUID.fromString(accountNumber);
        } catch (IllegalArgumentException e) {
            // accountNumber = new String();
            // invalidate();
            // return null;
            try {
                accept = UIUtil.get_input(sc, accept, prompt + " Invalid account number. "
                        + "\n Press any key to return to the account menu.", (String s) -> {
                            return true;
                        });
            } catch (Exception q) {
                e.printStackTrace();
            }
            return new ExitItem(this.parent);
        }
        Account account = AccountController.getInstance().getAccount(accountNumber);
        BigDecimal startAmount = account.getAmount();
        get_display_string();

        try {
            depositAmount = UIUtil.get_input(sc, depositAmount, prompt + " Current account balance: " + startAmount.toString() + ". \n Enter deposit amount: ", (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        AccountController.getInstance().deposit(account, new BigDecimal(depositAmount));

        totalAmount = account.getAmount().toString();

        try {
            accept = UIUtil.get_input(sc, accept, prompt + " Amount paid. Total amount on account = " + totalAmount
                    + "\n Press any key to continue.", (String s) -> {
                        return true;
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO update deposit to return change
        // if(change > 0)
        // {
        //     try {
        //         accept = UIUtil.get_input(sc, accept, prompt + " Change due to customer: " + change
        //                 + "\n Press any key to return to the account menu.", (String s) -> {
        //                     return true;
        //                 });
        //     } catch (Exception e) {
        //         e.printStackTrace();
        //     }
        // }

        return new ExitItem(this.parent);
    }

}