package MicroServiceHuman;

import Actor.Actor;
import MicroServiceSamu.Hotline;

public class Patient implements Actor {
    private int id;
    private int phoneNumber;
    private String firstName;
    private String lastName;

    public Patient(int id, String firstName, String lastName, int phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public void call(Hotline hotline, String message) {
        hotline.receiveMessage(message);
    }

    @Override
    public void receiveMessage(String msg) {

    }

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
