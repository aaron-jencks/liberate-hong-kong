package company.Controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Entity.SQLPerson;

public class PersonController extends SQLController {

    private static PersonController controllerInstance = null;

    private static String PERSON_FIRSTNAME_CONST = "first";
    private static String PERSON_LASTNAME_CONST = "last";
    private static String TABLE_NAME = "PERSON";

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
        SQLPerson p = null;
        String sqlQuery = "SELECT * FROM " + TABLE_NAME +
                    " WHERE ID = " + sqlPrepare(id.toString());
          
        if (SQLController.debug) {
            System.out.println("executeQuery : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                p = createPerson(resultSet);
            }
        } catch (SQLException se) {
            debugError(se);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    /**
     * Create a person with the supplied names
     * returns the newly created person
     */
    public SQLPerson createPerson(String firstName, String lastName){
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO " + TABLE_NAME +
                    " ( ID, " + PERSON_FIRSTNAME_CONST + ", " + PERSON_LASTNAME_CONST + " )" +
                    " VALUES ( " + 
                    sqlPrepare(id.toString()) + " , " +
                    sqlPrepare(firstName) + " , " +
                    sqlPrepare(lastName) + " )";
        executeUpdate(sql);
        return new SQLPerson(id, firstName, lastName);
    }

    /**
     * Create a person from a result set
     * @param personResult
     * @return
     */
    private SQLPerson createPerson(ResultSet personResult){
        SQLPerson p = new SQLPerson();
        try {
            p.setFirstName(personResult.getString(PERSON_FIRSTNAME_CONST));
            return p;
        } catch (SQLException e) {
            debugError(e);
        }
        try {
            p.setLastName(personResult.getString(PERSON_LASTNAME_CONST));
            return p;
        } catch (SQLException e) {
            debugError(e);
        }
        try {
            p.setId(UUID.fromString(personResult.getString("ID")));
            return p;
        } catch (SQLException e) {
            debugError(e);
        }
        return null;
    }

    /**
     * Get all the people in the db
     * @return
     */
    public ArrayList<SQLPerson> getAll(){
        ArrayList<SQLPerson> allPerson = new ArrayList<>();
        String sqlQuery = "SELECT * " + TABLE_NAME;
        if(SQLController.debug){
            System.out.println("executeQuery : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()){
                SQLPerson p = createPerson(resultSet);
                allPerson.add(p);
            }
        } catch (SQLException se) {
            debugError(se);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allPerson;
    }

    /**
     * Delete a person
     * @param person
     */
    public void deletePerson(SQLPerson person){
        if(person == null){
            return;
        }
        delete(TABLE_NAME, person.getId().toString());   
    }
    /**
     * Create the table in sql
     * Will only create if it doesn't exist
     */
    private static void createTable(){
        String[] params = {
            PERSON_FIRSTNAME_CONST + " VARCHAR(255)",
            PERSON_LASTNAME_CONST + " VARCHAR(255)",
        };
        create(TABLE_NAME, params);
    }

    /**
     * Truncate the table
     */
    public void truncateTable(){
        truncate(TABLE_NAME);
    }

    /**
     * Drop the table
     */
    public void dropTable(){
        drop(TABLE_NAME);
    }
}
