package company.Controller.Abstract;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;

import company.Controller.Interface.ILockboxController;
import company.Entity.Lockbox;

public abstract class ALockboxController extends ASQLController implements ILockboxController {

    public Lockbox getLockbox(String id){
        return getLockbox(UUID.fromString(id));
    }

    /**
     * Find a lockbox by id
     * @param id
     * @return
     */
    public Lockbox getLockbox(UUID id){
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + 
                    " WHERE ID = " + sqlPrepare(id);
        Lockbox b = null;
        if(ASQLController.debug){
            System.out.println("execute query : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while(resultSet.next()){
                b = createLockbox(resultSet);
            }
        } catch (SQLException e) {
            debugError(e);
        } finally{
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
        return b;
    }

    /**
     * Create a new lockbox
     * @param description
     * @param password
     * @return
     */
    public Lockbox createLockbox(String description, String password){
        UUID id = UUID.randomUUID();
        String sql = "INSERT INTO " + TABLE_NAME + 
                    " ( ID, " + 
                    implode(new String[]{
                        BOX_DESC,
                        BOX_PASSWORD
                    }) + 
                    " ) VALUES ( " +
                    implode(new String[]{
                        sqlPrepare(id),
                        sqlPrepare(description),
                        sqlPrepare(password)
                    }) + " ) ";
        executeUpdate(sql);
        return getLockbox(id);
    }

    private Lockbox createLockbox(ResultSet boxResult){
        UUID id = null;
        String description = null;
        String password = null;
        try {
            description = boxResult.getString(BOX_DESC);
        } catch (SQLException e) {
            System.err.println("Failed to get description of lockbox");
            debugError(e);
        }
        try {
            password = boxResult.getString(BOX_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Failed to get password for lockbox");
            debugError(e);
        }
        try {
            id = UUID.fromString(boxResult.getString("ID"));
        } catch (SQLException e) {
            System.err.println("Failed to get id for lockbox");
            debugError(e);
        }
        return new Lockbox(id, description, password);
    }

    /**
     * Get all the lockboxes from the DB
     * @return
     */
    public ArrayList<Lockbox> getAll(){
        ArrayList<Lockbox> allBoxes = new ArrayList<>();
        String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        if(ASQLController.debug){
            System.out.println("execute query : " + sqlQuery + "\n");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getDataSource().getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                allBoxes.add(createLockbox(resultSet));
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
        return allBoxes;
    }

    /**
     * Create the table
     */
    protected static void createTable(){
        String[] params = {
            BOX_DESC + " TEXT ",
            BOX_PASSWORD + " VARCHAR(255) ",
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

    /**
     * Delete a lockbox
     * @param box
     */
    public void deleteLockbox(Lockbox box){
        if(box == null){
            return;
        }
        deleteLockbox(box.getId());
    }

    /**
     * Delete a lockbox
     * @param id
     */
    public void deleteLockbox(UUID id){
        delete(TABLE_NAME, id);
    }

    /**
     * Update lockbox
     * @param box
     */
    public void updateLockbox(Lockbox box){
        String[] params = {
            BOX_DESC + " = " + sqlPrepare(box.getDescription()),
            BOX_PASSWORD + " = " + sqlPrepare(box.getPassword()),
        };
        update(TABLE_NAME, box.getId(), params);
    }
}