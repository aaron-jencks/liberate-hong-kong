package company;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.After;
import org.junit.Test;

import company.Entity.Person;
import company.Entity.Interface.IPerson;
import company.Entity.Interface.ISaveable;

public class PersonTest {

    @After
    public void clearFiles(){
        // ISaveable.clearFile(Person.class.getName());
    }

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
        Person p2 = IPerson.load(id);
        boolean isSame = p.getFirstName().equals(p2.getFirstName());
        boolean sameId = p.getObjectId().equals(p2.getObjectId());
        assertTrue("Loaded person names do not match { " + p.getFirstName() + " != " + p2.getFirstName() +  " }", isSame);
        assertTrue("Loaded persons do not have same objIds { " + p.getObjectId() + " != " + p2.getObjectId() + " }", sameId);
    }

    /**
     * Try using a setter
     */
    @Test
    public void personSetTest(){
        Person p = new Person("first", "last");
        UUID id = p.setFirstName("notFirst");
        Person p2 = IPerson.load(id);
        boolean isSame = p.getFirstName().equals(p2.getFirstName());
        assertTrue("Loaded person with setter changed name not equal. { " + p.getFirstName() + " != " + p2.getFirstName() + " }", isSame);
    }
}