package sample.objects;

public class Employee {
    private String FirstName;
    private String LastName;
    private int id ;

    public Employee(String firstName, String lastName, int id) {
        FirstName = firstName;
        LastName = lastName;
        this.id = id;
    }

    public Employee() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "FirstName='" + FirstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", id=" + id +
                '}';
    }
}
