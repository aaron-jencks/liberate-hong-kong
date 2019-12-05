package UI.menus.PayCreditAccountMenu;

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

public class PayCreditAccountMenu extends AMenu {
    private String accountNumber = new String();
    private String depositAmount = new String();
    private String accept = new String();
    private String totalAmount = new String();

    @Override
    public String get_display_string() {
        String result = new String();
 
        String s = "";
        if (accountNumber.isEmpty()) {
            s = "Please enter the account number you would like to pay on: ";
        } else if (depositAmount.isEmpty()) {
            s = "How much would you like to pay today: ";
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
            try {
                accept = UIUtil.get_input(sc, accept, prompt + "Cannot pay on the credit account because the bank is locked.", (String s) -> { return true; });
            } catch (Exception er) {
                er.printStackTrace();
            }
            return new ExitItem(this.parent);
        }

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

        try {
            AccountController.getInstance().deposit(account, new BigDecimal(depositAmount));
        }
        catch(BankLockedException e) {
            try {
                accept = UIUtil.get_input(sc, accept, prompt + "Cannot pay on the credit account because the bank is locked.", (String s) -> { return true; });
            } catch (Exception er) {
                er.printStackTrace();
            }
            return new ExitItem(this.parent);
        }
        totalAmount = account.getAmount().toString();

        toast("Amount paid. Total amount on account = " + totalAmount);
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

        return new ExitItem();
    }

}