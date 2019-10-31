package UI.menus.DeleteAccountMenu;

import java.util.Scanner;

import UI.AMenu;
import UI.IMenuItem;
import UI.UIUtil;
import UI.controller.ITermController;
import company.Entity.BankAccount;
import company.Entity.Abstract.AAccount;
import company.Entity.Abstract.ABankAccount;

public class DeleteAccountMenu extends AMenu {

    private String accountId = new String();

    public DeleteAccountMenu(ITermController parent) {
        super(parent);
        // TODO Auto-generated constructor stub
    }


    @Override
    public IMenuItem prompt() {
        Scanner sc = new Scanner(System.in);

        if(!is_valid)
            display();

        try { accountId = UIUtil.get_input(sc, accountId, prompt, (String s) -> { return true; }); }
        catch(Exception e) { e.printStackTrace(); }

        get_display_string();

        BankAccount ba = ABankAccount.findById(accountId);
        long amt = ba.closeAccount();

        return null;
    }
}