package residential.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import residential.model.Resident;

public class ResidentService {

        // Search for residents by name
        public ArrayList<Resident> searchResidents(
                ArrayList<Resident> residents,
                String searchName) {

                ArrayList<Resident> results = new ArrayList<>();

                for (Resident resident : residents) {

                if (resident.getFullName()
                        .toLowerCase()
                        .contains(searchName.toLowerCase())) {

                        results.add(resident);
                }
                }

                return results;
        }

        // Find an exact resident by name
        public Resident findResident(
                ArrayList<Resident> residents,
                String name) {

                for (Resident resident : residents) {

                if (resident.getFullName()
                        .equalsIgnoreCase(name)) {

                        return resident;
                }
                }

                return null;
        }

        // Remove a resident
        public boolean removeResident(
                ArrayList<Resident> residents,
                Resident resident) {

                if (resident == null) {
                return false;
                }

                boolean removed = residents.remove(resident);

                if (removed) {
                saveAllResidents(residents);
                }

                return removed;
        }

        // Save one resident
        public void saveResident(Resident resident) {

                try (
                        FileWriter fileWriter =
                                new FileWriter("residents.txt", true);
                        PrintWriter writer =
                                new PrintWriter(fileWriter)) {

                writer.println(
                        "======================================"
                );

                writer.println(
                        "          RESIDENT INFORMATION"
                );

                writer.println(
                        "======================================"
                );

                writer.println(
                        "Name: " + resident.getFullName()
                );

                writer.println(
                        "Gender: " + resident.getGender()
                );

                writer.println(
                        "Age: " + resident.getAge()
                );

                writer.println(
                        "Address: " + resident.getAddress()
                );

                writer.println(
                        "Contact: " + resident.getContactNumber()
                );

                writer.println(
                        "Civil Status: " + resident.getCivilStatus()
                );

                writer.println(
                        "Occupation: " + resident.getOccupation()
                );

                writer.println();

                } catch (IOException e) {

                System.out.println(
                        "\nError saving resident information."
                );
                }
        }

        // Save all residents
        public void saveAllResidents(
                ArrayList<Resident> residents) {

                try (
                        FileWriter fileWriter =
                                new FileWriter("residents.txt");
                        PrintWriter writer =
                                new PrintWriter(fileWriter)) {

                for (Resident resident : residents) {

                        writer.println(
                                "======================================"
                        );

                        writer.println(
                                "          RESIDENT INFORMATION"
                        );

                        writer.println(
                                "======================================"
                        );

                        writer.println(
                                "Name: " + resident.getFullName()
                        );

                        writer.println(
                                "Gender: " + resident.getGender()
                        );

                        writer.println(
                                "Age: " + resident.getAge()
                        );

                        writer.println(
                                "Address: " + resident.getAddress()
                        );

                        writer.println(
                                "Contact: " + resident.getContactNumber()
                        );

                        writer.println(
                                "Civil Status: " + resident.getCivilStatus()
                        );

                        writer.println(
                                "Occupation: " + resident.getOccupation()
                        );

                        writer.println();
                }

                } catch (IOException e) {

                System.out.println(
                        "\nError saving resident information."
                );
                }
        }

        // Load residents from file
        public void loadResidents(
                ArrayList<Resident> residents) {

                File file = new File("residents.txt");

                if (!file.exists()) {
                return;
                }

                try (Scanner fileScanner = new Scanner(file)) {

                String fullName = "";
                String gender = "";
                int age = 0;
                String address = "";
                String contactNumber = "";
                String civilStatus = "";
                String occupation = "";

                while (fileScanner.hasNextLine()) {

                        String line = fileScanner.nextLine();

                        if (line.startsWith("Name: ")) {

                        fullName = line.substring(6);

                        } else if (line.startsWith("Gender: ")) {

                        gender = line.substring(8);

                        } else if (line.startsWith("Age: ")) {

                        age = Integer.parseInt(
                                line.substring(5)
                        );

                        } else if (line.startsWith("Address: ")) {

                        address = line.substring(9);

                        } else if (line.startsWith("Contact: ")) {

                        contactNumber = line.substring(9);

                        } else if (line.startsWith("Civil Status: ")) {

                        civilStatus = line.substring(14);

                        } else if (line.startsWith("Occupation: ")) {

                        occupation = line.substring(11);

                        Resident resident = new Resident(
                                fullName,
                                age,
                                gender,
                                address,
                                contactNumber,
                                civilStatus,
                                occupation
                        );

                        residents.add(resident);

                        fullName = "";
                        gender = "";
                        age = 0;
                        address = "";
                        contactNumber = "";
                        civilStatus = "";
                        occupation = "";
                        }
                }

                } catch (IOException e) {

                System.out.println(
                        "\nError loading resident information."
                );

                } catch (NumberFormatException e) {

                System.out.println(
                        "\nError reading resident age."
                );
                }
        }
}

