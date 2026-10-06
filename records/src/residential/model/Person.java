package residential.model;

public class Person {

    // Private fields demonstrate encapsulation
    private String fullName;
    private int age;
    private String gender;

    // Constructor
    public Person(String fullName, int age, String gender) {

        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    // Setters
    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Method that can be overridden
    public void displayBasicInformation() {

        System.out.println("Name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }
}