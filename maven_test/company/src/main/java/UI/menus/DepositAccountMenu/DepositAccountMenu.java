package UI.menus.DepositAccountMenu;

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
import company.exceptions.BankLockedException;

public class DepositAccountMenu extends AMenu {
    private String accountNumber = new String();
    private String depositAmount = new String();
    private String accept = new String();
    private String totalAmount = new String();

    @Override
    public String get_display_string() {
        String result = new String();
 
        String s = "";
        if (accountNumber.isEmpty()) {
            s = "Please enter the account number you would like to deposit into: ";
        } else if (depositAmount.isEmpty()) {
            s = "How much would you like to deposit today: ";
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

        Account a;
        //try to get the account
        try {
            a = AccountController.getInstance().getAccount(accountNumber);
        }
        catch (BankLockedException e) {
            try {
                accept = UIUtil.get_input(sc, accept, prompt + "Cannot get the bank account because the bank is locked.", (String s) -> { return true; });
            } catch (Exception er) {
                er.printStackTrace();
            }
            return new ExitItem(this.parent);
        }

        //make sure it exists
        if(a == null){
            toast("Invalid account number.");
            return new ExitItem();
        }

        BigDecimal startAmount = a.getAmount();
        get_display_string();

        try {
            AnsiUtil.append_display_string(get_x_coord(), prompt + " Current account balance: " + startAmount.toString() + ". \n Enter deposit amount: ");
            depositAmount = UIUtil.get_input(sc, depositAmount, "", (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();

        try {
            //try to deposit
            AccountController.getInstance().deposit(a, new BigDecimal(depositAmount));
            totalAmount = a.getAmount().toString();

            toast("Amount deposited. Total amount on account = " + totalAmount);

            return new ExitItem();
        } catch (InvalidParameterException e) {
            toast("Failed to deposit amount.");
            return new ExitItem();
        } catch (BankLockedException e) {
            toast("Cannot deposit money because bank is locked");
            return new ExitItem();
        }

    }

}