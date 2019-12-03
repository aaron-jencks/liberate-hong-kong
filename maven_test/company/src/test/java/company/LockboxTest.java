package company;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

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

    @Test
    public void testAttributes(){
        String d = "this is a description, it can be very long bc its a text field, let's see how long it can get before something blows up, I wonder how it will break, idk, udk, maybe it wont, maybe it will, I need to make something fancy for the UI to make inputting all this description easy";
        String p = "pass";
        Lockbox b = bc.createLockbox(d,p);
        assertEquals(b.getDescription(), d);
    }
}