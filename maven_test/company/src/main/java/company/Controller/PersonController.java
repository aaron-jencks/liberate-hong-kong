package company.Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.SQLPerson;

public class PersonController extends SQLController {

    private static PersonController controllerInstance = null;

    private static String PERSON_ID_CONST = "ID";
    private static String PERSON_FIRSTNAME_CONST = "first";
    private static String PERSON_LASTNAME_CONST = "last";
    private static String PERSON_TABLE_NAME = "PERSON";

    /**
     * Make this controller a singleton
     * @return
     */
    public static PersonController getInstance(){
        if (controllerInstance == null) {
            createTable();
            controllerInstance = new PersonController();
        }
        return controllerInstance;
    }

    /**
     * Find the person by the id
     */
    public SQLPerson getPerson(UUID id){
        String sql = "SELECT * FROM " + PERSON_TABLE_NAME +
                    " WHERE " + PERSON_ID_CONST + " = " + sqlPrepare(id.toString());
        return createPerson(execute(sql));
    }

    /**
     * Create a person with the supplied names
     * returns the newly created person
     */
    public SQLPerson createPerson(String firstName, String lastName){
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO " + PERSON_TABLE_NAME +
                    " ( " + PERSON_ID_CONST + ", " + PERSON_FIRSTNAME_CONST + ", " + PERSON_LASTNAME_CONST + " )" +
                    " VALUES ( " + 
                    sqlPrepare(id.toString()) + " , " +
                    sqlPrepare(firstName) + " , " +
                    sqlPrepare(lastName) + " )";
        executeUpdate(sql);
        return getPerson(id);
    }

    private SQLPerson createPerson(ResultSet personResult){
        try {
            SQLPerson p = new SQLPerson();
            p.setFirstName(personResult.getString(PERSON_FIRSTNAME_CONST));
            p.setLastName(personResult.getString(PERSON_LASTNAME_CONST));
            p.setId(UUID.fromString(personResult.getString(PERSON_ID_CONST)));
            return p;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return null;
    }

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<SQLPerson> getAll(){
        ArrayList<SQLPerson> allPerson = new ArrayList<>();
        String sql = "SELECT * " + PERSON_TABLE_NAME;
        ResultSet list = execute(sql);
        try {
            while (list.next()){
                SQLPerson p = createPerson(list);
                allPerson.add(p);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return allPerson;
    }

    /**
     * Create the table in sql
     * Will only create if it doesn't exist
     */
    private static void createTable(){
        String sql = "CREATE TABLE IF NOT EXISTS " + PERSON_TABLE_NAME + 
                    "(" + PERSON_ID_CONST + " VARCHAR(255) not NULL, " + 
                    PERSON_FIRSTNAME_CONST + " VARCHAR(255), " + 
                    PERSON_LASTNAME_CONST + " VARCHAR(255), " + 
                    " PRIMARY KEY ( " + PERSON_ID_CONST + " ))";
        executeUpdate(sql);
    }

    /**
     * Drop the table
     */
    public void dropTable(){
        String sql = "DROP TABLE " + PERSON_TABLE_NAME;
        executeUpdate(sql);
    }
}
