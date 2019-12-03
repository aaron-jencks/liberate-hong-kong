package company;

import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import company.Controller.LockboxController;
import company.Entity.Lockbox;

public class LockboxTest{
    private static LockboxController bc;

    @AfterClass
    public static void truncate(){
        LockboxController.getInstance().truncateTable();
    }

    @Before
    public void setup(){
        bc = LockboxController.getInstance();
    }

    @Test
    public void testCreateBox(){
        bc.createLockbox("This is a lockbox", "pass");
    }

    @Test
    public void testCreateError(){
        Lockbox b = bc.getLockbox(UUID.randomUUID());
        assertNull(b);
    }

    @Test
    public void testDelete(){
        Lockbox b = bc.createLockbox("d", "p");
        bc.deleteLockbox(b);
        b = bc.getLockbox(b.getId());
        assertNull(b);
    }
}