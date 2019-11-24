package UI.menus.WithdrawlAccountMenu;

import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.UUID;

import UI.AMenu;
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

    public WithdrawlAccountMenu(ITermController parent) {
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
             s = "Please enter the account number you would like to withdrawl from: ";
         } else if (withdrawlAmount.isEmpty()) {
             s = "How much would you like to withdrawl today: ";
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
        } catch (Exception e) {
            try {
                accept = UIUtil.get_input(sc, accept, prompt + " Account not found. " + totalAmount
                        + "\n Press any key to return to the account menu.", (String s) -> {
                            return true;
                        });
            } catch (Exception er) {
                er.printStackTrace();
            }
            return new ExitItem(this.parent);
        }
        Account a = AccountController.getInstance().getAccount(accountNumber);
        BigDecimal startAmount = a.getAmount();
        get_display_string();

        try {
            withdrawlAmount = UIUtil.get_input(sc, withdrawlAmount, prompt + " Current account balance: " + startAmount.toString() + ". \n Enter withdraw amount: ", (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        try {
            AccountController.getInstance().withdrawl(a, new BigDecimal(withdrawlAmount));
        } catch (InvalidParameterException e) {
            try {
                accept = UIUtil.get_input(sc, accept, prompt + " Insufficent funds "
                        + "\n Press any key to return to the account menu.", (String s) -> {
                            return true;
                        });
            } catch (Exception er) {
                er.printStackTrace();
            }
    
            return new ExitItem(this.parent);
        }

        totalAmount = a.getAmount().toString();

        try {
            accept = UIUtil.get_input(sc, accept, prompt + " Amount withdrawn. Total amount on account = " + totalAmount
                    + "\n Press any key to return to the account menu.", (String s) -> {
                        return true;
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ExitItem(this.parent);
    }

}