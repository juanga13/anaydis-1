package anaydis.sort.practice.tp1;

public class FullName {
    private String firstname;
    private String lastname;

    public FullName(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("\n").append(lastname).append(", ").append(firstname).toString();
    }

    public String getFirstName() {
        return firstname;
    }
    public String getLastName() {
        return lastname;
    }
}
