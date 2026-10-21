package residential.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoginService {

    public static final int MAX_ATTEMPTS = 3;
    public static final int LOCKOUT_SECONDS = 120;

    // Validate credentials. IllegalArgumentException is unchecked.
    private void validateCredentials(String username, String password)
            throws IllegalArgumentException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
    }

    // Check login credentials
    public boolean login(String username, String password) {
        try {
            validateCredentials(username, password);
        } catch (IllegalArgumentException e) {
            System.out.println("\nInvalid login input: " + e.getMessage());
            return false;
        }

        File file = new File("accounts.txt");
        try {
            if (!file.exists()) {
                return false;
            }
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String[] account = fileScanner.nextLine().split(",", -1);
                    if (account.length == 2
                            && account[0].equals(username)
                            && account[1].equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("\nError reading account information.");
        } catch (SecurityException e) {
            System.out.println("\nPermission denied while reading account information.");
        }
        return false;
    }

    // Check if username already exists
    public boolean usernameExists(String username) {
        if (username == null || username.trim().isEmpty()) {
            System.out.println("\nInvalid username: Username cannot be empty.");
            return false;
        }

        File file = new File("accounts.txt");
        try {
            if (!file.exists()) {
                return false;
            }
            try (Scanner fileScanner = new Scanner(file)) {
                while (fileScanner.hasNextLine()) {
                    String[] account = fileScanner.nextLine().split(",", -1);
                    if (account.length == 2 && account[0].equals(username)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("\nError checking account information.");
        } catch (SecurityException e) {
            System.out.println("\nPermission denied while checking account information.");
        }
        return false;
    }

    // Save new account
    public void saveAccount(String username, String password) {
        try {
            validateCredentials(username, password);
        } catch (IllegalArgumentException e) {
            System.out.println("\nCannot save account: " + e.getMessage());
            return;
        }

        boolean saved = false;
        try (
                FileWriter fileWriter = new FileWriter("accounts.txt", true); PrintWriter writer = new PrintWriter(fileWriter)) {
            writer.println(username + "," + password);
            // PrintWriter may hide write errors unless checkError() is used.
            if (writer.checkError()) {
                throw new IOException("Account data could not be written.");
            }
            saved = true;
        } catch (IOException | SecurityException e) {
            // The finally block reports the failed save.
        } finally {
            if (!saved) {
                System.out.println("\nError saving account. Check file access and permissions.");
            }
        }
    }
}
