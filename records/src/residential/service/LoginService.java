
package residential.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoginService {

    public static final int MAX_ATTEMPTS = 3;
    public static final int LOCKOUT_SECONDS = 120;

    // Check login credentials
    public boolean login(String username, String password) {

        File file = new File("accounts.txt");

        if (!file.exists()) {
            return false;
        }

        try (Scanner fileScanner = new Scanner(file)) {

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] account = line.split(",");

                if (account.length == 2) {

                    String savedUsername = account[0];
                    String savedPassword = account[1];

                    if (savedUsername.equals(username)
                            && savedPassword.equals(password)) {

                        return true;
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "\nError reading account information."
            );
        }

        return false;
    }

    // Check if username already exists
    public boolean usernameExists(String username) {

        File file = new File("accounts.txt");

        if (!file.exists()) {
            return false;
        }

        try (Scanner fileScanner = new Scanner(file)) {

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] account = line.split(",");

                if (account.length == 2) {

                    if (account[0].equals(username)) {

                        return true;
                    }
                }
            }

        } catch (IOException e) {

            System.out.println(
                    "\nError checking account information."
            );
        }

        return false;
    }

    // Save new account
    public void saveAccount(String username, String password) {

        try (
                FileWriter fileWriter =
                        new FileWriter("accounts.txt", true);

                PrintWriter writer =
                        new PrintWriter(fileWriter)
        ) {

            writer.println(username + "," + password);

        } catch (IOException e) {

            System.out.println(
                    "\nError saving account."
            );
        }
    }
}