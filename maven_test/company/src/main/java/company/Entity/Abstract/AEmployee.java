package company.Entity.Abstract;

import java.util.UUID;

import company.Entity.Interface.IEmployee;

public abstract class AEmployee extends APerson implements IEmployee
{
    private String employeeUsername = new String();
    private String employeePassword = new String();
    private String employeeSecurityQuestion = new String();
    private String employeeSecurityAnswer = new String();


    public AEmployee(String firstName, String lastName, String employeeUsername) {
        super(firstName, lastName);
        this.employeeUsername = employeeUsername;
        this.employeePassword = "";
        this.save();
    }

    public AEmployee(APerson person){
        super(person.getFirstName(), person.getLastName());
        this.employeeUsername = null;
        this.employeePassword = "";
        this.setObjectId(person.getObjectId());
        this.save();
        person.delete();
    }

    public AEmployee(APerson person, String employeeUsername){
        super(person.getFirstName(), person.getLastName());
        this.employeeUsername = employeeUsername;
        this.employeePassword = "";
        this.setObjectId(person.getObjectId());
        this.save();
        person.delete();
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

    public UUID setEmployeePassword(String employeePassword)
    {
        this.employeePassword = employeePassword;
        return this.save();
    }

    public String getEmployeeUsername()
    {
        return employeeUsername;
    }

    public UUID setEmployeeUsername(String employeeUsername)
    {
        this.employeeUsername = employeeUsername;
        return this.save();
    }

    public String getEmployeeSecurityQuestion()
    {
        return employeeSecurityQuestion;
    }

    public UUID setEmployeeSecurityQuestion(String employeeSecurityQuestion)
    {
        this.employeeSecurityQuestion = employeeSecurityQuestion;
        return this.save();
    }

    public String getEmployeeSecurityAnswer()
    {
        return employeeSecurityAnswer;
    }

    public UUID setEmployeeSecurityAnswer(String employeeSecurityAnswer)
    {
        this.employeeSecurityAnswer = employeeSecurityAnswer;
        return this.save();
    }

}
