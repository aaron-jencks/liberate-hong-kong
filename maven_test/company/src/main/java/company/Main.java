package company;

import java.io.IOException;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;

import UI.AnsiUtil;
import UI.UIUtil;
import UI.controller.*;

public class Main {

    public static void main(String[] args) {
        // System.out.println("MySQL JDBC Connection Testing ~");

        // List<Person> result = new ArrayList<>();

        // String SQL_SELECT = "Select * from Person";

        // try (Connection conn = DriverManager.getConnection(
        //         "jdbc:mysql://127.0.0.1:3306/java_test", "java", "java-sucks");
        //      PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT)) {

        //     ResultSet resultSet = preparedStatement.executeQuery();

        //     while (resultSet.next()) {

        //         long id = resultSet.getLong("id");
        //         String name = resultSet.getString("firstName");
        //         System.out.println(Long.toString(id) + " " +  name);
        //     }
        //     result.forEach(x -> System.out.println(x));

        // } catch (SQLException e) {
        //     System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        try {

            TermController term = new TermController();

            AnsiUtil.display_window(term, true, UIUtil.create_box_string("Hello world!\nThis is Aaron."));

        }
        catch(IOException e) {
            System.out.println("Something dumb happened");
        }
    }
}
