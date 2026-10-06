package residential.model;

public class Resident extends Person implements Identifiable {

    // Private fields demonstrate encapsulation
    private String address;
    private String contactNumber;
    private String civilStatus;
    private String occupation;

    // Static field
    private static int residentCount = 0;

    // Static block
    static {

        residentCount = 0;
        System.out.println("Resident class loaded.");
    }

    // Constructor
    public Resident(
            String fullName,
            int age,
            String gender,
            String address,
            String contactNumber,
            String civilStatus,
            String occupation) {

        // Calls the superclass constructor
        super(fullName, age, gender);

        this.address = address;
        this.contactNumber = contactNumber;
        this.civilStatus = civilStatus;
        this.occupation = occupation;

        // Shared by all Resident objects
        residentCount++;
    }

    // Getters

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public String getOccupation() {
        return occupation;
    }

    // Setters

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    // Static method
    public static int getResidentCount() {

        return residentCount;
    }

    // Method overriding
    @Override
    public void displayBasicInformation() {

        System.out.println("Name: " + getFullName());
        System.out.println("Age: " + getAge());
        System.out.println("Gender: " + getGender());
        System.out.println("Address: " + address);
        System.out.println("Contact: " + contactNumber);
        System.out.println("Civil Status: " + civilStatus);
        System.out.println("Occupation: " + occupation);
    }

    // Interface method implementation
    @Override
    public void displayIdentification() {

        System.out.println("Resident Name: " + getFullName());
        System.out.println("Address: " + address);
    }
}