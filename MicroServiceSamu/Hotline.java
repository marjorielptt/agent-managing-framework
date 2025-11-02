package MicroServiceSamu;

import Actor.Actor;

public class Hotline implements Actor {
    private int id;
    private String firstName;
    private String lastName;

    public Hotline(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public void receiveMessage(String msg) {
        // Receive call from hurt people, and redirect if assistance is needed
    }

    public void call(Actor actor, String msg) {

        // Based on decision, call needed actor
        actor.receiveMessage(msg);
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
