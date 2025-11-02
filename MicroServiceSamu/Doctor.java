package MicroServiceSamu;

import Actor.Actor;

public class Doctor implements Actor {
    private int id;
    private String firstName;
    private String lastName;

    public Doctor(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void receiveMessage(String msg) {

    }

    public void seDeplacer() {}

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
