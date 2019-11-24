package company.Entity;

import java.util.UUID;

import company.Controller.EmployeeController;
import company.Entity.Enum.Position;

public class Employee extends Person {
    
    private String username;
    private String password;
    private String question;
    private String answer;
    private Position position;

    public Employee(UUID id, Position position, String username, String password, String question, String answer, String first, String last){
        super(id, first, last);
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.position = position;
    }

    public Employee(UUID id, Position position, String username, String password, String question, String answer, Person person){
        super(id, person.getFirstName(), person.getLastName());
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
        this.position = position;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
        EmployeeController.getInstance().updateEmployee(this);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        EmployeeController.getInstance().updateEmployee(this);
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
        EmployeeController.getInstance().updateEmployee(this);
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
        EmployeeController.getInstance().updateEmployee(this);
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "{" +
            " username='" + username + "'" +
            ", password='" + password + "'" +
            ", question='" + question + "'" +
            ", answer='" + answer + "'" +
            ", position='" + position + "'" +
            "}";
    }

}