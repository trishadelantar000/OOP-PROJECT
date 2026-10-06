package residential.ui;

import java.util.ArrayList;
import java.util.Scanner;
import residential.model.Resident;
import residential.model.SeniorResident;
import residential.service.ResidentService;

public class Main {

        private static final ResidentService residentService =
                new ResidentService();

        public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);

                ArrayList<Resident> residents = new ArrayList<>();

                // Load saved residents
                residentService.loadResidents(residents);

                int choice;

                // Display the menu until the user picks 7
                do {

                        System.out.println();
                        System.out.println("============================================");
                        System.out.println("      BARANGAY RESIDENT RECORD SYSTEM       ");
                        System.out.println("============================================");
                        System.out.println("========== MENU ==========");
                        System.out.println("1 - Register Resident");
                        System.out.println("2 - View Residents");
                        System.out.println("3 - Search Resident");
                        System.out.println("4 - Update Resident");
                        System.out.println("5 - Remove Resident");
                        System.out.println("6 - View Resident Count");
                        System.out.println("7 - Exit");

                        System.out.print("What do you want to do? ");
                        choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {

                                // Register resident
                                case 1 -> {

                                        System.out.print("\nEnter full name: ");
                                        String fullName = scanner.nextLine();

                                        System.out.print("Enter age: ");
                                        int age = scanner.nextInt();
                                        scanner.nextLine();

                                        System.out.print("Enter gender: ");
                                        String gender = scanner.nextLine();

                                        System.out.print("Enter address: ");
                                        String address = scanner.nextLine();

                                        System.out.print("Enter contact number: ");
                                        String contactNumber = scanner.nextLine();

                                        System.out.print("Enter civil status: ");
                                        String civilStatus = scanner.nextLine();

                                        System.out.print("Enter occupation: ");
                                        String occupation = scanner.nextLine();

                                        System.out.print(
                                                "Is this resident a senior citizen? (Y/N): "
                                        );

                                        String seniorAnswer = scanner.nextLine();

                                        Resident resident;

                                        // Check senior citizen status
                                        if (seniorAnswer.equalsIgnoreCase("Y")) {

                                                resident = new SeniorResident(
                                                        fullName,
                                                        age,
                                                        gender,
                                                        address,
                                                        contactNumber,
                                                        civilStatus,
                                                        occupation,
                                                        true
                                                );

                                        } else {

                                                resident = new Resident(
                                                        fullName,
                                                        age,
                                                        gender,
                                                        address,
                                                        contactNumber,
                                                        civilStatus,
                                                        occupation
                                                );
                                        }

                                        // Add resident to list
                                        residents.add(resident);

                                        // Save resident
                                        residentService.saveResident(resident);

                                        System.out.println(
                                                "\nResident registered successfully!"
                                        );
                                }

                                // Display resident list
                                case 2 -> {

                                        if (residents.isEmpty()) {

                                                System.out.println(
                                                        "\nNo resident records found."
                                                );

                                        } else {

                                                System.out.println(
                                                        "\nResident Information:"
                                                );

                                                System.out.println(
                                                        "====================="
                                                );

                                                for (Resident resident : residents) {

                                                        System.out.println(
                                                                "======================================"
                                                        );

                                                        resident.displayBasicInformation();
                                                }
                                        }
                                }

                                // Search a resident
                                case 3 -> {

                                        if (residents.isEmpty()) {

                                                System.out.println(
                                                        "\nNo resident records found."
                                                );

                                                break;
                                        }

                                        System.out.print(
                                                "\nEnter name to search: "
                                        );

                                        String searchName = scanner.nextLine();

                                        ArrayList<Resident> results =
                                                residentService.searchResidents(
                                                        residents,
                                                        searchName
                                                );

                                        System.out.println(
                                                "\nSearch Results:"
                                        );

                                        System.out.println(
                                                "================"
                                        );

                                        if (results.isEmpty()) {

                                                System.out.println(
                                                        "No resident found with that name."
                                                );

                                        } else {

                                                for (Resident resident : results) {

                                                        System.out.println(
                                                                "======================================"
                                                        );

                                                        resident.displayBasicInformation();
                                                }
                                        }
                                }

                                // Update a resident's information
                                case 4 -> {

                                        updateResident(residents, scanner);
                                }

                                // Remove a resident
                                case 5 -> {

                                        if (residents.isEmpty()) {

                                                System.out.println(
                                                        "\nNo resident records found."
                                                );

                                                break;
                                        }

                                        System.out.print(
                                                "\nEnter name of resident to remove: "
                                        );

                                        String removeName = scanner.nextLine();

                                        Resident resident =
                                                residentService.findResident(
                                                        residents,
                                                        removeName
                                                );

                                        if (resident == null) {

                                                System.out.println(
                                                        "\nResident not found."
                                                );

                                                break;
                                        }

                                        System.out.println(
                                                "\nResident Found!"
                                        );

                                        resident.displayBasicInformation();

                                        System.out.print(
                                                "\nAre you sure you want to remove "
                                                + "this resident? (Y/N): "
                                        );

                                        String confirm = scanner.nextLine();

                                        // Confirm removal
                                        if (confirm.equalsIgnoreCase("Y")) {

                                                residentService.removeResident(
                                                        residents,
                                                        resident
                                                );

                                                System.out.println(
                                                        "\nResident removed successfully!"
                                                );

                                        } else {

                                                System.out.println(
                                                        "\nRemoval cancelled."
                                                );
                                        }
                                }

                                // Display resident count
                                case 6 -> {

                                        System.out.println(
                                                "\nTotal Resident Objects Created: "
                                                + Resident.getResidentCount()
                                        );
                                }

                                case 7 -> {

                                        System.out.println(
                                                "\nThank you for using the system!"
                                        );
                                }

                                default -> {

                                        System.out.println(
                                                "\nInvalid choice."
                                        );
                                }
                        }

                } while (choice != 7);

                scanner.close();
        }


        // Update resident information
        public static void updateResident(
                ArrayList<Resident> residents,
                Scanner input) {

                if (residents.isEmpty()) {

                        System.out.println(
                                "\nNo resident records found."
                        );

                        return;
                }

                System.out.print(
                        "\nEnter name of resident to update: "
                );

                String updateName = input.nextLine();

                ArrayList<Resident> results =
                        residentService.searchResidents(
                                residents,
                                updateName
                        );

                if (results.isEmpty()) {

                        System.out.println(
                                "\nNo resident found with that name."
                        );

                        return;
                }

                System.out.println(
                        "\nResidents Found:"
                );

                System.out.println(
                        "================"
                );

                for (int i = 0; i < results.size(); i++) {

                        Resident resident = results.get(i);

                        System.out.println(
                                "\n[" + (i + 1) + "] "
                                + resident.getFullName()
                        );

                        resident.displayBasicInformation();

                        System.out.println(
                                "======================================"
                        );
                }

                int selected;

                while (true) {

                        System.out.print(
                                "\nEnter the number of the resident to update: "
                        );

                        selected = input.nextInt();
                        input.nextLine();

                        if (selected >= 1 && selected <= results.size()) {
                                break;
                        }

                        System.out.println(
                                "\nInvalid selection. Please choose a number from 1 to "
                                + results.size() + "."
                        );
                }

                Resident resident = results.get(selected - 1);

                boolean updating = true;

                while (updating) {

                        System.out.println(
                                "\n======================================"
                        );

                        System.out.println(
                                "           UPDATE RESIDENT"
                        );

                        System.out.println(
                                "======================================"
                        );

                        System.out.println(
                                "Selected Resident: "
                                + resident.getFullName()
                        );

                        System.out.println();

                        System.out.println("1. Update Name");
                        System.out.println("2. Update Age");
                        System.out.println("3. Update Gender");
                        System.out.println("4. Update Address");
                        System.out.println("5. Update Contact Number");
                        System.out.println("6. Update Civil Status");
                        System.out.println("7. Update Occupation");
                        System.out.println("8. Cancel Update");
                        System.out.println("9. Save and Exit Update");

                        System.out.print("\nEnter choice: ");

                        int choice = input.nextInt();
                        input.nextLine();

                        switch (choice) {

                                case 1 -> {

                                        System.out.print(
                                                "Enter new name: "
                                        );

                                        resident.setName(
                                                input.nextLine()
                                        );

                                        System.out.println(
                                                "\nName changed."
                                        );
                                }

                                case 2 -> {

                                        System.out.print(
                                                "Enter new age: "
                                        );

                                        int newAge = input.nextInt();
                                        input.nextLine();

                                        resident.setAge(newAge);

                                        System.out.println(
                                                "\nAge changed."
                                        );
                                }

                                case 3 -> {

                                        System.out.print(
                                                "Enter new gender: "
                                        );

                                        resident.setGender(
                                                input.nextLine()
                                        );

                                        System.out.println(
                                                "\nGender changed."
                                        );
                                }

                                case 4 -> {

                                        System.out.print(
                                                "Enter new address: "
                                        );

                                        resident.setAddress(
                                                input.nextLine()
                                        );

                                        System.out.println(
                                                "\nAddress changed."
                                        );
                                }

                                case 5 -> {

                                        System.out.print(
                                                "Enter new contact number: "
                                        );

                                        resident.setContactNumber(
                                                input.nextLine()
                                        );

                                        System.out.println(
                                                "\nContact number changed."
                                        );
                                }

                                case 6 -> {

                                        System.out.print(
                                                "Enter new civil status: "
                                        );

                                        resident.setCivilStatus(
                                                input.nextLine()
                                        );

                                        System.out.println(
                                                "\nCivil status changed."
                                        );
                                }

                                case 7 -> {

                                        System.out.print(
                                                "Enter new occupation: "
                                        );

                                        resident.setOccupation(
                                                input.nextLine()
                                        );

                                        System.out.println(
                                                "\nOccupation changed."
                                        );
                                }

                                case 8 -> {

                                        System.out.println(
                                                "\nUpdate cancelled."
                                        );

                                        updating = false;
                                }

                                case 9 -> {

                                        residentService.saveAllResidents(
                                                residents
                                        );

                                        System.out.println(
                                                "\nChanges have been saved."
                                        );

                                        updating = false;
                                }

                                default -> {

                                        System.out.println(
                                                "\nInvalid choice."
                                        );
                                }
                        }
                }
        }


}

