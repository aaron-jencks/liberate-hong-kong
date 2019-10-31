package company.Entity.Abstract;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.Interface.IEmployee;

public abstract class AEmployee extends APerson implements IEmployee
{

    protected long employeeID;
    protected String employeeUsername;
    protected String employeePassword;
    protected String employeeSecurityQuestion;
    protected String employeeSecurityAnswer;

    public static String LookupSecurityQuestion(String username){
        JSONArray emps = ASaveable.loadAllAsJson("Employee");
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username) {
                return obj.getString("employeeSecurityQuestion");
            }
        }
        return null;
    }

    public static boolean CheckSecurityQuestion(String username, String answer){
        JSONArray emps = ASaveable.loadAllAsJson("Employee");
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username) && obj.getString("employeeSecurityAnswer").equals(answer)){
                return true;
            }
        }
        return false;
    }

    public static boolean CheckPassword(String username, String password){
        JSONArray emps = ASaveable.loadAllAsJson("Employee");
        for (int i = 0; i < emps.length(); i++) {
            JSONObject obj = emps.getJSONObject(i);
            if(obj.getString("employeeUsername").equals(username) && obj.getString("employeePassword").equals(password)){
                return true;
            }
        }
        return false;
    }

    public static boolean CheckEmployee(String username){
        String s = Paths.get("").toAbsolutePath().toString();
        File file = new File(s + "\\data\\company.Entity.Employee.json");
        try {
            Scanner sc = new Scanner(file);
            String content = "";
            while (sc.hasNextLine()) {
                // get the next line in file
                String line = sc.nextLine();
                content += line;
            }
            sc.close();
            // make the string into json
            JSONArray arr = new JSONArray(content);
            // check if it has the ID we are searching for
            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = arr.getJSONObject(i);
                String id = obj.get("username").toString();
                if (!id.equals(username)) {
                    continue;
                }
                // instantiate it into an object
                Object inst = instantiate(removeLeadingA(obj.get("type").toString()));
                Class<?> clazz = inst.getClass();
                // fill all the attributes
                Field[] fields = clazz.getSuperclass().getDeclaredFields();
                if (fields.length == 0) {
                    fields = clazz.getSuperclass().getSuperclass().getDeclaredFields();
                }
                for (Field f : fields) {
                    f.set(inst, obj.get(f.getName()));
                }
                sc.close();
                return true;
            }

        } catch (FileNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getEmployeeID()
    {
        return employeeID;
    }

    public AEmployee setEmployeeID(long employeeID)
    {
        this.employeeID = employeeID;
        return this;
    }

    public String getEmployeePassword()
    {
        return employeePassword;
    }

    public AEmployee setEmployeePassword(String employeePassword)
    {
        this.employeePassword = employeePassword;
        return this;
    }

    public String getEmployeeUsername()
    {
        return employeeUsername;
    }

    public AEmployee setEmployeeUsername(String employeeUsername)
    {
        this.employeeUsername = employeeUsername;
        return this;
    }

    public String getEmployeeSecurityQuestion()
    {
        return employeeSecurityQuestion;
    }

    public AEmployee setEmployeeSecurityQuestion(String employeeSecurityQuestion)
    {
        this.employeeSecurityQuestion = employeeSecurityQuestion;
        return this;
    }

    public String getEmployeeSecurityAnswer()
    {
        return employeeSecurityAnswer;
    }

    public AEmployee setEmployeeSecurityAnswer(String employeeSecurityAnswer)
    {
        this.employeeSecurityAnswer = employeeSecurityAnswer;
        return this;
    }

}
