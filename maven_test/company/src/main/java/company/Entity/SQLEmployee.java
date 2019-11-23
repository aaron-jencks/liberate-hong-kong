package company.Entity;

import java.util.UUID;

public class SQLEmployee {
    
    
    private UUID id;
    private String username;
    private String password;
    private String question;
    private String answer;

    public SQLEmployee(){

    }


    public SQLEmployee(UUID id, Enum position, String username, String password, String question, String answer) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQuestion() {
        return this.question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public SQLEmployee id(UUID id) {
        this.id = id;
        return this;
    }

    public SQLEmployee username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", question='" + getQuestion() + "'" +
            ", answer='" + getAnswer() + "'" +
            "}";
    }


}