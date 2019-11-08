package company.Entity.Abstract;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import company.Entity.Interface.IEmployee;

public abstract class AEmployee extends APerson implements IEmployee
{
    protected String employeeUsername = new String();
    protected String employeePassword = new String();
    protected String employeeSecurityQuestion = new String();
    protected String employeeSecurityAnswer = new String();


    public AEmployee(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName);
        this.employeeUsername = employeeUsername;
        this.employeePassword = "";
    }

    public AEmployee(){
        super();
        this.employeePassword = null;
        this.employeeSecurityAnswer = null;
        this.employeeSecurityQuestion = null;
        this.employeeUsername = null;
    }

    public String getEmployeePassword()
    {
        return employeePassword;
    }

    public IEmployee setEmployeePassword(String employeePassword)
    {
        this.employeePassword = employeePassword;
        this.save();
        return this;
    }

    public String getEmployeeUsername()
    {
        return employeeUsername;
    }

    public IEmployee setEmployeeUsername(String employeeUsername)
    {
        this.employeeUsername = employeeUsername;
        this.save();
        return this;
    }

    public String getEmployeeSecurityQuestion()
    {
        return employeeSecurityQuestion;
    }

    public IEmployee setEmployeeSecurityQuestion(String employeeSecurityQuestion)
    {
        this.employeeSecurityQuestion = employeeSecurityQuestion;
        this.save();
        return this;
    }

    public String getEmployeeSecurityAnswer()
    {
        return employeeSecurityAnswer;
    }

    public IEmployee setEmployeeSecurityAnswer(String employeeSecurityAnswer)
    {
        this.employeeSecurityAnswer = employeeSecurityAnswer;
        this.save();
        return this;
    }

}
