package Actor;

public interface Actor {
    String getFirstName();
    void setFirstName(String name);

    String getLastName();
    void setLastName(String lastName);

    void receiveMessage(String msg);
}
