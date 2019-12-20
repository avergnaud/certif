package ppa.labs.oca.stream.model;

public class Actor {

    private String firstName;
    private String lastName;
    private int age;

    public Actor(final String firstName, final String lastName, final int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
