public class Actors {
    private String firstName;
    private String lastName;
    private int actorId;

    public Actors(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Actors(){}

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getActorId() {
        return actorId;
    }
    public void setActorId(int actorId) {
        this.actorId = actorId;
    }
}


