package company.Entity;

import java.util.UUID;

import company.Controller.LockboxController;

public class Lockbox{
    private UUID id;
    private String description;
    private String password;

    public Lockbox(UUID id, String description, String password){
        this.id = id;
        this.description = description;
        this.password = password;
    }

    public UUID getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
        LockboxController.getInstance().updateLockbox(this);
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
        LockboxController.getInstance().updateLockbox(this);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", description='" + getDescription() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}