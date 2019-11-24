package company.Entity;

import java.util.UUID;

import company.Controller.PersonController;

public class Person {
    private UUID id;
    private String firstName;
    private String lastName;

    /**
     * Empty Constructor
     */
    public Person(){
        this.id = UUID.randomUUID();
        this.lastName = null;
        this.firstName = null;
    }

    public Person(String firstname, String lastname){
        super();
        this.firstName = firstname;
        this.lastName = lastname;
    }

    /**
     * Full Constructor
     * @param id
     * @param firstName
     * @param lastName
     */
    public Person(UUID id, String firstName, String lastName){
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public UUID getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        PersonController.getInstance().updatePerson(this);
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        PersonController.getInstance().updatePerson(this);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            "}";
    }

}
