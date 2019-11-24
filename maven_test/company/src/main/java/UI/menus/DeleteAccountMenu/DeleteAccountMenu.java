package UI.menus.DeleteAccountMenu;

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

public class DeleteAccountMenu extends AMenu {

    private String accountId = new String();
    private String accept = new String();

    public DeleteAccountMenu(ITermController parent) {
        super(parent);
        // TODO Auto-generated constructor stub
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
        if (accountId.isEmpty()) {
            s = "Account id: ";
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
            accountId = UIUtil.get_input(sc, accountId, prompt, (String s) -> {
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        get_display_string();
        
        Account a = AccountController.getInstance().getAccount(accountId);
        if (a == null) {
            try {
                accept = UIUtil.get_input(sc, accept, prompt + " No account was found with the given account id", (String s) -> {
                            return true;
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            BigDecimal amt = AccountController.getInstance().deleteAccount(a);
            try {
                accept = UIUtil.get_input(sc, accept, prompt + " The account with the given account id has been closed. The balance remaining on the account was " + amt.toString(), (String s) -> {
                            return true;
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return new ExitItem(this.parent);
    }
}