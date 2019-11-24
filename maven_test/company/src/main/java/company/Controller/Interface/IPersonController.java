package company.Controller.Interface;

import java.util.ArrayList;
import java.util.UUID;

import company.Entity.Person;

public interface IPersonController{
    
    static String PERSON_FIRSTNAME_CONST = "first";
    static String PERSON_LASTNAME_CONST = "last";
    static String TABLE_NAME = "PERSON";

    /**
     * Get person by id
     * @param id
     * @return
     */
    public Person getPerson(UUID id);

    /**
     * Get all people
     * @return
     */
    public ArrayList<Person> getAll();

    /**
     * Create a new person
     * @param first
     * @param last
     * @return
     */
    public Person createPerson(String first, String last);

    /**
     * Delete a person (remove them from db by id)
     * @param person
     */
    public void deletePerson(Person person);
    
}