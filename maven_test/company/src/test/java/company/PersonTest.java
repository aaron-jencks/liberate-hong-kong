package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import company.Entity.Person;
import company.Entity.Abstract.APerson;

public class PersonTest {

    /**
     * Rigorous Test :-)
     * Sanity check
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    /**
     * Make sure you can save/load person
     */
    @Test
    public void personSimpleTest(){
        Person p = new Person("first", "last");
        UUID id =  p.save();
        
    }
}