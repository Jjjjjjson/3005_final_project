import javax.xml.namespace.QName;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ClubManagement {

    // Database connection parameters
    private static final String URL = "jdbc:postgresql://localhost:5432/project_database";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";
    private static final Scanner scanner = new Scanner(System.in);
    // Formatter to parse dates in the ISO_LOCAL_DATE format
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    // Main menu of the interface
    public static void main(String[] args) {
        ClubManagement app = new ClubManagement();
        boolean running = true;
        // Main loop for user interaction
        while (running) {
            System.out.println("------Welcome to the Health and Fitness Club Management System!!!!!------");
            System.out.println("1. User Login");
            System.out.println("2. Trainer Login");
            System.out.println("3. Administrator Login");
            System.out.println("4. Exit Program");
            System.out.print("Choose an option: \n");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    app.userLogin();
                    break;
                case 2:
                    app.trainerLogin();
                    break;
                case 3:
                    app.adminLogin();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    running = false;
                    break;
                default:
                    System.out.println("---Invalid option. Please choose again---");
            }
            System.out.println("\n------------------------------------------------\n");
        }
    }


    /**
     * Interface selection when login
     */

    // User login interaction
    private void userLogin() {
        boolean userSession = true;
        while (userSession) {
            System.out.println("\n------User Login------");
            System.out.println("1. Existing User Login");
            System.out.println("2. Register as New User");
            System.out.println("3. Exit User Section");
            System.out.print("Choose an option: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loginUser(); // Existing user login
                    break;
                case 2:
                    registerNewUser(); // new user register
                    break;
                case 3:
                    System.out.println("Exiting User Section...");
                    userSession = false; // Exit the loop
                    break;
                default:
                    System.out.println("---Invalid option. Please choose again---");
            }
        }
    }

    // Member login section
    private void loginUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM Member WHERE Username = ? AND Password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nLogin successful. Welcome, " + username + "!");
                    userDashboard(username); // Call user dashboard after successful login
                } else {
                    System.out.println("Login failed. Incorrect username or password.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    // Trainer login interaction
    private void trainerLogin() {
        boolean trainerSession = true;
        while (trainerSession) {
            System.out.println("\n------Trainer Login------");
            System.out.println("1. Existing trainer Login");
            System.out.println("2. Register as new trainer");
            System.out.println("3. Exit Trainer Section");
            System.out.print("Choose an option: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loginTrainer();
                    break;
                case 2:
                    registerTrainer();
                    break;
                case 3:
                    System.out.println("Exiting Trainer Section...");
                    trainerSession = false; // Exit the loop
                    break;
                default:
                    System.out.println("---Invalid option. Please choose again---");
            }
        }
    }

    // Train login section
    private void loginTrainer() {
        System.out.print("Enter your trainer username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your trainer password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM Trainer WHERE Username = ? AND Password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nLogin successful. Welcome, " + username + "!");
                    trainerDashboard(username); // Call trainer dashboard after successful login
                } else {
                    System.out.println("Login failed. Incorrect username or password.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    // Administrator login interaction
    private void adminLogin() {
        boolean adminSession = true; // Control the login loop
        while (adminSession) {
            System.out.println("\n------Admin Login------");
            System.out.println("1. Existing admin Login");
            System.out.println("2. Register as new admin");
            System.out.println("3. Exit Admin Section");
            System.out.print("Choose an option: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    loginAdmin();
                    break;
                case 2:
                    registerAdmin();
                    break;
                case 3:
                    System.out.println("Exiting Admin Section...");
                    adminSession = false; // Exit the loop
                    break;
                default:
                    System.out.println("---Invalid option. Please choose again---");
            }
        }
    }

    private void loginAdmin() {
        System.out.print("Enter your admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your admin password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM Administrator WHERE Username = ? AND Password = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("\nLogin successful. Welcome, " + username + "!");
                    adminDashboard(username); // Call trainer dashboard after successful login
                } else {
                    System.out.println("Login failed. Incorrect username or password.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }



    /**
     * Detailed function for register user, trainer and admin.
     */
    // Register new user
    private void registerNewUser() {
        // User input for new user details
        System.out.print("Enter new username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter new email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter new address: ");
        String address = scanner.nextLine();

        // SQL query to insert the new user
        String query = "INSERT INTO Member (Username, Password, Email, Address) VALUES (?, ?, ?, ?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Setting query parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            // Execute the update
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("\nUser registered successfully.\n");
            } else {
                System.out.println("User registration failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    // Register new trainer
    private void registerTrainer() {
        System.out.print("Enter new trainer username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        // SQL query to insert the new user
        String query = "INSERT INTO Trainer (Username, Password) VALUES (?, ?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Setting query parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // Execute the update
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("\nTrainer registered successfully.\n");
            } else {
                System.out.println("Trainer registration failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

    // Register new admin
    private void registerAdmin() {
        System.out.print("Enter new admin username: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

        // SQL query to insert the new user
        String query = "INSERT INTO Administrator (Username, Password) VALUES (?, ?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Setting query parameters
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            // Execute the update
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("\nAdministrator registered successfully.\n");
            } else {
                System.out.println("Administrator registration failed.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }



    /**
     * Interface dashboard selection after login
     */

    // User interface after login
    private void userDashboard(String username) {
        boolean userDashboardSituation = true;
        while (userDashboardSituation) {
            System.out.println("\n------User Dashboard------\n");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Add Dashboard");
            System.out.println("4. Dashboard display");
            System.out.println("5. Schedule Management");
            System.out.println("6. Exit current site");
            System.out.print("Choose an option: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewUserProfile();
                    break;
                case 2:
                    updateUserProfile();
                    break;
                case 3:
                    addDashboard();
                    break;
                case 4:
                    displayDashboard();
                    break;
                case 5:
                    scheduleManagement();
                    break;
                case 6:
                    System.out.println("Exiting current site...");
                    userDashboardSituation = false; // Exit the loop
                    break;
                default:
                    System.out.println("---Invalid option. Please choose again---");
            }
        }
    }

    // Trainer interface after login
    private void trainerDashboard(String username) {
        boolean trainerDashboardSituation = true;
        while (trainerDashboardSituation) {
            System.out.println("\n------Trainer Dashboard------\n");
            System.out.println("1. Schedule availability");
            System.out.println("2. View member by name");
            System.out.println("3. Exit current site");
            System.out.print("Choose an option: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    scheduleAvailability();
                    break;
                case 2:
                    viewMember();
                    break;
                case 3:
                    System.out.println("Exiting current site...");
                    trainerDashboardSituation = false; // Exit the loop
                    break;
                default:
                    System.out.println("---Invalid option. Please choose again---");
            }
        }
    }

    // Administrator interface after login
    private void adminDashboard(String username) {
        boolean adminSession = true;
        while (adminSession) {
            System.out.println("\n------Administrator Dashboard------\n");
            System.out.println("1. Room Booking Management");
            System.out.println("2. Equipment Maintenance Monitor");
            System.out.println("3. Class Schedule Updating");
            System.out.println("4. Billing and Payment Processing");
            System.out.println("5. Exit current site");
            System.out.print("Choose an option: \n");

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (adminChoice) {
                case 1:
                    manageRoomBooking();
                    break;
                case 2:
                    viewEquipmentStatus();
                    break;
                case 3:
                    updateClassSchedule();
                    break;
                case 4:
                    manageBilling();
                    break;
                case 5:
                    System.out.println("Exiting current site...");
                    adminSession = false;
                    break;
                default:
                    System.out.println("---Invalid option. Please choose again---");
            }
        }
    }



    /**
     * Detailed function for User interface after login.
     */

    // View User Profile function
    private void viewUserProfile() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine(); // Read the username from input

        // SQL query to fetch user profile details based on username.
        String query = "SELECT Username, Email, Address, FitnessGoals, HealthMetrics, ExerciseRoutines FROM Member WHERE Username = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Setting the query parameter for the Username.
            pstmt.setString(1, username);

            // Execute the query and obtain the result set.
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // Retrieve data from each column in the result set.
                String email = rs.getString("Email");
                String address = rs.getString("Address");
                String fitnessGoals = rs.getString("FitnessGoals");
                String healthMetrics = rs.getString("HealthMetrics");
                String exerciseRoutines = rs.getString("ExerciseRoutines");

                // Display the retrieved data.
                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Address: " + address);
                System.out.println("Fitness Goals: " + fitnessGoals);
                System.out.println("Health Metrics: " + healthMetrics);
                System.out.println("Exercise Routines: " + exerciseRoutines);
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    // Update User Profile function
    public void updateUserProfile() {
        System.out.print("Enter your username: ");
        String currentUsername = scanner.nextLine(); // Read the current username from input

        System.out.print("Enter new username: (leave blank to retain current)");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new email: (leave blank to retain current)");
        String email = scanner.nextLine();
        System.out.print("Enter new address: (leave blank to retain current)");
        String address = scanner.nextLine();
        System.out.print("Enter new fitness goals: (leave blank to retain current)");
        String fitnessGoals = scanner.nextLine();
        System.out.print("Enter new health metrics: (leave blank to retain current)");
        String healthMetrics = scanner.nextLine();
        System.out.print("Enter new exercise routines: (leave blank to retain current)");
        String exerciseRoutines = scanner.nextLine();

        // Query to update the user profile based on username instead of user ID
        String query = "UPDATE Member SET Username = COALESCE(NULLIF(?, ''), Username), " +
                "Email = COALESCE(NULLIF(?, ''), Email), " +
                "Address = COALESCE(NULLIF(?, ''), Address), " +
                "FitnessGoals = COALESCE(NULLIF(?, ''), FitnessGoals), " +
                "HealthMetrics = COALESCE(NULLIF(?, ''), HealthMetrics), " +
                "ExerciseRoutines = COALESCE(NULLIF(?, ''), ExerciseRoutines) " +
                "WHERE Username = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set parameters for the update statement
            pstmt.setString(1, newUsername.isEmpty() ? null : newUsername);
            pstmt.setString(2, email.isEmpty() ? null : email);
            pstmt.setString(3, address.isEmpty() ? null : address);
            pstmt.setString(4, fitnessGoals.isEmpty() ? null : fitnessGoals);
            pstmt.setString(5, healthMetrics.isEmpty() ? null : healthMetrics);
            pstmt.setString(6, exerciseRoutines.isEmpty() ? null : exerciseRoutines);
            pstmt.setString(7, currentUsername);

            // Execute the update
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Profile updated successfully.");
            } else {
                System.out.println("No profile update occurred. Please check if the username is correct.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Add dashboard data
    private void addDashboard(){
        System.out.print("Enter the username of the member to add dashboard data: ");
        String username = scanner.nextLine(); // Read the username from input

        // Ask for the details to be added to the dashboard
        System.out.print("Enter exercise routines: ");
        String exerciseRoutines = scanner.nextLine();
        System.out.print("Enter fitness achievements: ");
        String fitnessAchievements = scanner.nextLine();
        System.out.print("Enter health statistics: ");
        String healthStatistics = scanner.nextLine();

        // SQL query to insert data into the Dashboard table. This requires a JOIN to find the MemberID based on the username.
        String query = "INSERT INTO Dashboard (MemberID, ExerciseRoutines, FitnessAchievements, HealthStatistics) " +
                "SELECT MemberID, ?, ?, ? FROM Member WHERE Username = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set parameters for the insert statement
            pstmt.setString(1, exerciseRoutines);
            pstmt.setString(2, fitnessAchievements);
            pstmt.setString(3, healthStatistics);
            pstmt.setString(4, username);

            // Execute the insert
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Dashboard data added successfully.");
            } else {
                System.out.println("No data was added. Please check if the username is correct and exists.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Display User Dashboard
    private void displayDashboard() {
        System.out.print("Enter your username to display the dashboard: ");
        String username = scanner.nextLine(); // Read the username from input

        // Query to fetch the exercise routines, fitness achievements, and health statistics of the member
        // using a JOIN with the Member table to match by username.
        String query = "SELECT d.ExerciseRoutines, d.FitnessAchievements, d.HealthStatistics " +
                "FROM Dashboard d JOIN Member m ON d.MemberID = m.MemberID " +
                "WHERE m.Username = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set the Username in the query
            pstmt.setString(1, username);

            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the member has a dashboard entry
                if (!rs.isBeforeFirst()) {
                    System.out.println("Dashboard data not found for the given username.");
                    return;
                }

                // If a record is found, print the dashboard information
                while (rs.next()) {
                    String exerciseRoutines = rs.getString("ExerciseRoutines");
                    String fitnessAchievements = rs.getString("FitnessAchievements");
                    String healthStatistics = rs.getString("HealthStatistics");

                    System.out.println("\nDashboard for Username: " + username);
                    System.out.println("Exercise Routines: " + exerciseRoutines);
                    System.out.println("Fitness Achievements: " + fitnessAchievements);
                    System.out.println("Health Statistics: " + healthStatistics);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // User schedule management interface
    private void scheduleManagement() {
        boolean running = true;
        while (running) {
            System.out.println("\n------Schedule Management------");
            System.out.println("1. View all trainers");
            System.out.println("2. View available trainers");
            System.out.println("3. Schedule group fitness class");
            System.out.println("4. Exit Schedule Management");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline left-over

            switch (choice) {
                case 1:
                    viewAllTrainers();
                    break;
                case 2:
                    viewAvailableTrainers();
                    break;
                case 3:
                    scheduleGroupFitnessClass();
                    break;
                case 4:
                    System.out.println("Exiting Schedule Management...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    // User can view all trainer
    private void viewAllTrainers() {
        System.out.println("List of All Trainers:");

        // SQL query to select trainer ID and username from the Trainer table
        String query = "SELECT TrainerID, Username FROM Trainer;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {  // Check if the result set is empty
                System.out.println("No trainers found.");
                return;
            }

            while (rs.next()) {
                int trainerID = rs.getInt("TrainerID");
                String username = rs.getString("Username");
                System.out.println("Trainer ID: " + trainerID + ", Name: " + username);
            }

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void viewAvailableTrainers() {
        System.out.println("Available Trainers:");

        // Query to fetch available trainers
        String query = "SELECT TrainerID, Username, Availability FROM Trainer WHERE Status = 'available'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No available trainers found at the moment.");
                return;
            }

            // Print out the list of available trainers
            System.out.println(String.format("%-10s %-20s %-15s", "Trainer ID", "Username", "Availability"));
            while (rs.next()) {
                int trainerId = rs.getInt("TrainerID");
                String username = rs.getString("Username");
                String availability = rs.getString("Availability");
                System.out.println(String.format("%-10d %-20s %-15s", trainerId, username, availability));
            }

            // Ask the user for their next action
            System.out.println("\nDo you want to schedule, reschedule, or cancel a training session? (Enter 'schedule', 'cancel', or 'none')");
            String action = scanner.nextLine().toLowerCase();

            switch (action) {
                case "schedule":
                    scheduleTrainingSession();
                    break;
                case "cancel":
                    cancelTrainingSession();
                    break;
                case "none":
                    System.out.println("No action chosen.");
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void scheduleTrainingSession() {
        System.out.print("Enter the Trainer ID to schedule with: ");
        int trainerId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Timestamp dateTime = null;
        while (dateTime == null) {
            System.out.print("Enter the date and time for your session (YYYY-MM-DD HH:MM:SS): ");
            String dateTimeStr = scanner.nextLine();
            try {
                // Attempt to convert String to Timestamp
                dateTime = Timestamp.valueOf(dateTimeStr);
            } catch (IllegalArgumentException e) {
                System.out.println("Incorrect time format, please try again.");
            }
        }

        String scheduleQuery = "INSERT INTO Training_Schedule (DateTime, TrainerID) VALUES (?, ?);";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(scheduleQuery)) {
            pstmt.setTimestamp(1, dateTime);  // Set the timestamp
            pstmt.setInt(2, trainerId);
            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Training session scheduled successfully.");
            } else {
                System.out.println("Failed to schedule the training session.");
            }
        } catch (SQLException e) {
            System.out.println("Error while scheduling session: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cancelTrainingSession() {
        System.out.println("Enter the ID of the training session you wish to cancel:");
        int sessionId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        // SQL query to delete the session from Training_Schedule based on the session ID
        String deleteQuery = "DELETE FROM Training_Schedule WHERE ScheduleID = ?;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(deleteQuery)) {
            pstmt.setInt(1, sessionId);  // Set the session ID parameter

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("Training session cancelled successfully.");
            } else {
                System.out.println("Failed to cancel the training session. Please check if the session ID is correct.");
            }
        } catch (SQLException e) {
            System.out.println("Error while cancelling session: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // TODO
    private void scheduleGroupFitnessClass() {
        System.out.println("Scheduling a Group Fitness Class........");

        // SQL query to display all upcoming fitness classes that haven't occurred yet.
        String query = "SELECT ClassID, ClassName, DateTime FROM Class WHERE DateTime > CURRENT_TIMESTAMP ORDER BY DateTime ASC;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {  // Check if the result set is empty
                System.out.println("No upcoming classes available.");
                return;
            }

            // Displaying available classes
            System.out.println("\n------Available Classes------");
            while (rs.next()) {
                int classID = rs.getInt("ClassID");
                String className = rs.getString("ClassName");
                String dateTime = rs.getString("DateTime");
                System.out.println("Class ID: " + classID + ", Class Name: " + className + ", Date/Time: " + dateTime);
            }

            // Asking the user to choose a class to join
            System.out.print("Enter the Class ID of the class you wish to join or 0 to cancel: ");
            int classID = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (classID == 0) {
                System.out.println("Class joining canceled.");
                return;
            }

            // Function to handle joining the class
            joinClass(classID);

        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void joinClass(int classID) {
        System.out.print("Enter your username to register for the class: ");
        String username = scanner.nextLine();

        // SQL query to find the MemberID from the username
        String findMemberIDQuery = "SELECT MemberID FROM Member WHERE Username = ?;";

        // SQL query to register the member for the selected class
        String joinQuery = "INSERT INTO ClassRegistrations (ClassID, MemberID) VALUES (?, ?) ON CONFLICT DO NOTHING;";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Prepare and execute the statement to find MemberID
            try (PreparedStatement findPstmt = conn.prepareStatement(findMemberIDQuery)) {
                findPstmt.setString(1, username);
                ResultSet rs = findPstmt.executeQuery();
                if (!rs.next()) {
                    System.out.println("Username not found.");
                    return;
                }
                int memberID = rs.getInt("MemberID");

                // Prepare and execute the statement to insert into ClassRegistrations
                try (PreparedStatement joinPstmt = conn.prepareStatement(joinQuery)) {
                    joinPstmt.setInt(1, classID);
                    joinPstmt.setInt(2, memberID);

                    int affectedRows = joinPstmt.executeUpdate();
                    if (affectedRows > 0) {
                        System.out.println("------You have successfully joined the class------");
                    } else {
                        System.out.println("Failed to join the class. You may already be registered or the class ID is invalid.");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }




    /**
     * Detailed function for Trainer interface after login.
     */

    // Trainer schedule Availability
    public void scheduleAvailability() {
        System.out.print("Enter your Trainer username: ");
        String username = scanner.nextLine();  // Read the username from input

        System.out.print("Enter your availability (e.g., Weekdays 9 AM - 5 PM): ");
        String availability = scanner.nextLine();

        System.out.print("Enter your status (available or unavailable): ");
        String status = scanner.nextLine();

        // Validate status input
        while (!status.equalsIgnoreCase("available") && !status.equalsIgnoreCase("unavailable")) {
            System.out.println("Invalid status. Please enter 'available' or 'unavailable'.");
            status = scanner.nextLine();
        }

        // Query to update the availability and status fields in the Trainer table using the trainer's username
        String query = "UPDATE Trainer SET Availability = ?, Status = ? WHERE Username = ?;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, availability);
            pstmt.setString(2, status);
            pstmt.setString(3, username);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Availability and status updated successfully.");
            } else {
                System.out.println("Failed to update availability and status. Please check if the username is correct.");
            }
        } catch (SQLException e) {
            System.out.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Trainer view member by name
    private void viewMember() {
        System.out.print("Enter member name to search: ");
        String memberName = scanner.nextLine();

        // Query to fetch member details by name
        String query = "SELECT MemberID, Username, Email, Address FROM Member WHERE Username LIKE ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + memberName + "%");  // Using LIKE for partial matching

            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {  // Check if the result set is empty
                    System.out.println("No member found with the name: " + memberName);
                    return;
                }

                System.out.println("\n------Member Details Found------");
                while (rs.next()) {
                    int memberID = rs.getInt("MemberID");
                    String username = rs.getString("Username");
                    String email = rs.getString("Email");
                    String address = rs.getString("Address");
                    System.out.println("\nMember ID: " + memberID + "\nUsername: " + username +
                            "\nEmail: " + email + "\nAddress: " + address);
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * Detailed function for Admin interface after login.
     */

    // Room Booking Management
    private void manageRoomBooking() {
        boolean manageRoomBookingSituation = true;
        while (manageRoomBookingSituation) {
            System.out.println("\n------Room Booking Management------");
            System.out.println("1. View Room Booking Situation");
            System.out.println("2. Change Room Booking Situation");
            System.out.println("3. Exit current site");
            System.out.print("Select an action: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewRoomBookingSituation();
                    break;
                case 2:
                    changeRoomBookingSituation();
                    break;
                case 3:
                    System.out.println("Exiting current site...");
                    manageRoomBookingSituation = false;
                    break;
                default:
                    System.out.println("Invalid option selected. Please try again.");
            }
        }
    }

    // Methods for the room booking actions
    private void viewRoomBookingSituation() {
        System.out.println("\n------Viewing current room bookings------");

        // SQL query to fetch all room bookings with booking ID, room name, datetime, and status
        String query = "SELECT bookingid, roomname, datetime, status FROM Room_booking;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (!rs.isBeforeFirst()) {  // Check if the result set is empty
                System.out.println("No room bookings found.");
                return;
            }

            while (rs.next()) {
                int bookingId = rs.getInt("bookingid");
                String roomName = rs.getString("roomname");
                String dateTime = rs.getString("datetime");
                String status = rs.getString("status");
                System.out.println("Booking ID: " + bookingId + ", Room Name: " + roomName + ", Date/Time: " + dateTime + ", Status: " + status);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Change room booking
    private void changeRoomBookingSituation() {
        System.out.println("Changing room booking situation...");

        System.out.print("Enter the Booking ID: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine(); // consume the leftover newline

        // Display the current status
        String fetchQuery = "SELECT roomname, datetime, status FROM Room_booking WHERE bookingid = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement fetchStmt = conn.prepareStatement(fetchQuery)) {
            fetchStmt.setInt(1, bookingId);
            try (ResultSet rs = fetchStmt.executeQuery()) {
                if (!rs.next()) {
                    System.out.println("No booking found with the given ID.");
                    return;
                }
                String roomName = rs.getString("roomname");
                String dateTime = rs.getString("datetime");
                String currentStatus = rs.getString("status");
                System.out.println("Current booking for " + roomName + " on " + dateTime + " is '" + currentStatus + "'.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        System.out.print("Enter new status (Available, Booked, Cancelled): ");
        String newStatus = scanner.nextLine();

        // Update the status
        String updateQuery = "UPDATE Room_booking SET status = ? WHERE bookingid = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
            updateStmt.setString(1, newStatus);
            updateStmt.setInt(2, bookingId);

            int affectedRows = updateStmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Booking status updated successfully.");
            } else {
                System.out.println("Failed to update booking status. Please check the booking ID and try again.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Function to monitor Equipment Maintenance
    private void viewEquipmentStatus() {
        // SQL query to fetch all equipment names and their maintenance status
        String query = "SELECT name, maintenancestatus FROM Equipment;";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n------Equipment Status Report------");
            if (!rs.isBeforeFirst()) {  // Check if the result set is empty
                System.out.println("No equipment found.");
                return;
            }

            while (rs.next()) {
                String name = rs.getString("name");
                String status = rs.getString("maintenanceStatus");
                System.out.println("Equipment Name: " + name + " ------ Status: " + status);
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Function to update class schedule
    private void updateClassSchedule() {
        System.out.println("Updating class schedule...");

        // Display all classes
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement("SELECT classid, classname, datetime FROM Class");
             ResultSet rs = pstmt.executeQuery()) {
            if (!rs.isBeforeFirst()) {
                System.out.println("No classes found.");
                return;
            }
            while (rs.next()) {
                System.out.println("Class ID: " + rs.getInt("classid") + ", Class Name: " + rs.getString("classname") + ", Date/Time: " + rs.getString("datetime"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        boolean validClassId = false;
        int classId = 0;
        while (!validClassId) {
            System.out.print("Enter the Class ID you want to update or type 'exit' to quit: ");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) return;

            try {
                classId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric Class ID.");
                continue;
            }

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement pstmt = conn.prepareStatement("SELECT datetime FROM Class WHERE classid = ?")) {
                pstmt.setInt(1, classId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        validClassId = true;
                        System.out.println("Current Date/Time for Class ID " + classId + ": " + rs.getString("datetime"));
                    } else {
                        System.out.println("Class not found, try again.");
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        // Prompt for new datetime until valid format is provided
        String newDatetime = null;
        while (true) {
            System.out.print("Enter the new datetime (YYYY-MM-DD HH:MM): ");
            newDatetime = scanner.nextLine();
            if (isValidDateTime(newDatetime)) {
                break;
            } else {
                System.out.println("Wrong format, please try again.");
            }
        }

        // Update the datetime if Class ID is valid
        String query = "UPDATE Class SET datetime = ? WHERE classid = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(newDatetime + ":00")); // appending seconds
            pstmt.setInt(2, classId);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Class schedule updated successfully.");
            } else {
                System.out.println("Failed to update class schedule.");
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isValidDateTime(String dateTimeStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateTimeStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Function to view billing and payment processing
    private void manageBilling() {
        boolean manageBillingSituation = true;
        while (manageBillingSituation) {
            System.out.println("\n------Billing and Payment Processing------");
            System.out.println("1. Show Unpaid Bill");
            System.out.println("2. Show Paid Bill");
            System.out.println("3. Exit current site");
            System.out.print("Select an action: \n");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showProcessingBill();
                    break;
                case 2:
                    showCompletedBill();
                    break;
                case 3:
                    System.out.println("Exiting current site...");
                    manageBillingSituation = false;
                    break;
                default:
                    System.out.println("Invalid option selected. Please try again.");
            }
        }
    }

    // Placeholder methods for billing actions
    private void showProcessingBill() {
        // SQL query to select unpaid bills
        String query = "SELECT BillingID, MemberID, Amount, DateTime, PaymentStatus FROM Billing WHERE PaymentStatus = 'Unpaid';";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Check if there are any results
            if (!rs.isBeforeFirst()) {
                System.out.println("No unpaid bills found.");
                return;
            }

            // Print out the result in a formatted manner
            System.out.println(String.format("%-10s %-10s %-10s %-20s %-15s", "Bill ID", "Member ID", "Amount", "Date", "Status"));
            while (rs.next()) {
                int billingId = rs.getInt("BillingID");
                int memberId = rs.getInt("MemberID");
                double amount = rs.getDouble("Amount");
                Timestamp dateTime = rs.getTimestamp("DateTime");
                String paymentStatus = rs.getString("PaymentStatus");

                System.out.println(String.format("%-10d %-10d %-10.2f %-20s %-15s", billingId, memberId, amount, dateTime.toString(), paymentStatus));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }


    private void showCompletedBill() {
        // SQL query to select paid bills
        String query = "SELECT BillingID, MemberID, Amount, DateTime, PaymentStatus FROM Billing WHERE PaymentStatus = 'Paid';";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Check if there are any results
            if (!rs.isBeforeFirst()) {
                System.out.println("No completed bills found.");
                return;
            }

            // Print the headers for the output
            System.out.println(String.format("%-10s %-10s %-10s %-20s %-15s", "Bill ID", "Member ID", "Amount", "Date", "Status"));

            // Iterate through the result set and print each bill
            while (rs.next()) {
                int billingId = rs.getInt("BillingID");
                int memberId = rs.getInt("MemberID");
                double amount = rs.getDouble("Amount");
                Timestamp dateTime = rs.getTimestamp("DateTime");
                String paymentStatus = rs.getString("PaymentStatus");

                System.out.println(String.format("%-10d %-10d %-10.2f %-20s %-15s", billingId, memberId, amount, dateTime.toString(), paymentStatus));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


}