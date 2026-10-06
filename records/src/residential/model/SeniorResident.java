package residential.model;

public class SeniorResident extends Resident {

    // Private field demonstrates encapsulation
    private boolean seniorCitizen;

    // Constructor
    public SeniorResident(
            String fullName,
            int age,
            String gender,
            String address,
            String contactNumber,
            String civilStatus,
            String occupation,
            boolean seniorCitizen) {

        // Calls Resident constructor
        super(
                fullName,
                age,
                gender,
                address,
                contactNumber,
                civilStatus,
                occupation
        );

        this.seniorCitizen = seniorCitizen;
    }

    // Getter
    public boolean isSeniorCitizen() {

        return seniorCitizen;
    }

    // Setter
    public void setSeniorCitizen(boolean seniorCitizen) {

        this.seniorCitizen = seniorCitizen;
    }

    // Method overriding
    @Override
    public void displayBasicInformation() {

        // Reuses Resident's display method
        super.displayBasicInformation();

        System.out.println(
                "Senior Citizen: "
                        + (seniorCitizen ? "Yes" : "No")
        );
    }
}