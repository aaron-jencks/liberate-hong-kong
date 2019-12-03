package company.exceptions;

import java.util.UUID;

public class BankLockedException extends Exception {

    public BankLockedException() {

        super("Action Cannot be completed because the bank is locked.");
    }
}