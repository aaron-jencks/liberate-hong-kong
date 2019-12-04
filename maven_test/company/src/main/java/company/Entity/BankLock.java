package company.Entity;

public class BankLock {
    private static BankLock bankLockInstance = null;
    private static boolean locked = false;

    private BankLock() {
        locked = false;
    }

    public static BankLock getInstance() {
        if (bankLockInstance == null) {
            bankLockInstance = new BankLock();
        }

        return bankLockInstance;
    }

    public void lockBank() {
        System.out.println("bank locked");
        locked = true;
    }

    public void unlockBank() {
        System.out.println("bank unlocked");
        locked = false;
    }

    public boolean isBankLocked() {
        return locked;
    }
}