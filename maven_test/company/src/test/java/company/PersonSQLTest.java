package company;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import company.Controller.PersonController;


/**
 * Unit test for simple App.
 */
public class PersonSQLTest 
{
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
     * Can I create the table?
     */
    @Test
    public void testTableCreate(){
        PersonController pc = PersonController.getInstance();
    }

    /**
     * Can I create a person?
     */
    @Test
    public void testCreatePerson(){
        PersonController pc = PersonController.getInstance();
        pc.createPerson("billy", "bob");
    }

}