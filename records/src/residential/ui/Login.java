package residential.ui;

import java.util.Scanner;
import residential.service.LoginService;

public class Login {

    // Static block
        static {
                System.out.println("Login system loaded.");
        }

        public static void main(String[] args) {

                Scanner scanner = new Scanner(System.in);
                LoginService loginService = new LoginService();

                boolean loggedIn = false;

                // Continue displaying menu until login
                while (!loggedIn) {

                System.out.println();
                System.out.println("======================================");
                System.out.println("       BARANGAY RESIDENT SYSTEM");
                System.out.println("======================================");
                System.out.println("1 - Login");
                System.out.println("2 - Sign Up");
                System.out.println("3 - Exit");
                System.out.println("======================================");

                System.out.print("Enter choice: ");
                String choice = scanner.nextLine();

                // Determine user's choice
                switch (choice) {

                        // Login
                        case "1" -> {

                        int attempts = 0;
                        boolean loginSuccessful = false;

                        while (attempts < LoginService.MAX_ATTEMPTS
                                && !loginSuccessful) {

                                System.out.println(
                                        "\n========== LOGIN =========="
                                );

                                System.out.print("Username: ");
                                String username = scanner.nextLine();

                                System.out.print("Password: ");
                                String password = scanner.nextLine();

                                if (loginService.login(username, password)) {

                                System.out.println(
                                        "\nLogin successful!"
                                );

                                System.out.println(
                                        "Welcome, " + username + "!"
                                );

                                loginSuccessful = true;
                                loggedIn = true;

                                Main.main(new String[0]);

                                } else {

                                attempts++;

                                int remainingAttempts
                                        = LoginService.MAX_ATTEMPTS - attempts;

                                System.out.println(
                                        "\nInvalid username or password."
                                );

                                if (remainingAttempts > 0) {

                                        System.out.println(
                                                "Attempts remaining: "
                                                + remainingAttempts
                                        );
                                }
                                }
                        }

                        // Lockout after maximum attempts
                        if (!loginSuccessful
                                && attempts >= LoginService.MAX_ATTEMPTS) {

                                System.out.println(
                                        "\n======================================"
                                );

                                System.out.println(
                                        "             LOGIN LOCKED"
                                );

                                System.out.println(
                                        "======================================"
                                );

                                System.out.println(
                                        "Too many failed login attempts."
                                );

                                System.out.println(
                                        "Please wait "
                                        + LoginService.LOCKOUT_SECONDS
                                        + " seconds."
                                );

                                for (int seconds = LoginService.LOCKOUT_SECONDS;
                                        seconds > 0;
                                        seconds--) {

                                System.out.print(
                                        "\rPlease wait: "
                                        + seconds
                                        + " seconds remaining..."
                                );

                                try {

                                        Thread.sleep(1000);

                                } catch (InterruptedException e) {

                                        Thread.currentThread()
                                                .interrupt();

                                        System.out.println(
                                                "\nLockout interrupted."
                                        );

                                        break;
                                }
                                }

                                System.out.println(
                                        "\n\nLockout finished."
                                );
                        }
                        }

                        // Sign Up
                        case "2" -> {

                        System.out.println(
                                "\n========== SIGN UP =========="
                        );

                        System.out.print(
                                "Create username: "
                        );

                        String username = scanner.nextLine();

                        System.out.print(
                                "Create password: "
                        );

                        String password = scanner.nextLine();

                        if (username.isEmpty()
                                || password.isEmpty()) {

                                System.out.println(
                                        "\nUsername and password cannot be empty."
                                );

                        } else if (loginService.usernameExists(username)) {

                                System.out.println(
                                        "\nUsername already exists."
                                );

                        } else {

                                loginService.saveAccount(
                                        username,
                                        password
                                );

                                System.out.println(
                                        "\nAccount created successfully!"
                                );
                        }
                        }

                        // Exit
                        case "3" -> {

                        System.out.println(
                                "\nThank you for using the system!"
                        );

                        scanner.close();

                        return;
                        }

                        default -> {

                        System.out.println(
                                "\nInvalid choice."
                        );
                        }
                }
                }

                scanner.close();
        }
}
