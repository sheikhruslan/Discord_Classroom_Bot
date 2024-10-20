package bot;


import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import tools.*;

/**
 * This bot is to manage the registration of the user with the command '/registration'.
 * The user will be asked to provide a registration code. If the registration code is
 * correct, the user will be registered to the system.
 *
 * The user cannot register again if the user has already registered (One discord ID
 * to one student ID).
 *
 * We assume the registration code will be sent to students via email in advanced.
 * The registration information is given in a file (please check users.csv)
 *
 * The file format of users.csv is as follows:
 * Each row may have three columns or five columns.
 * Three Columns, e.g.:
 *      20100001,g8xa9s,Bruce Lee
 * That represents the student ID is 20100001, the registration code is g8xa9s, and the name is Bruce Lee.
 * Five Columns, e.g.:
 *      20100002,-,Chan Tai Man,1004553330619580487,Kevin Wang
 * That represents the student ID is 20100002, the registration code is empty (registered already),
 * the student name is Chan Tai Man, the discord ID is 1004553330619580487, and the discord name is Kevin Wang.
 */
public class UserManagementBot extends CommandBot {
    protected ArrayList<User> users;
    private File inputFile;
    //Constructor
    public UserManagementBot(String filename) {
        users = new ArrayList<>();
        inputFile = new File(filename);
        addOption("regcode", "registration code", true);

        try {
            Scanner fileReader = new Scanner(inputFile); // load the users file into the program
            while (fileReader.hasNextLine()) {
                String[] tokens = fileReader.nextLine().split(",");
                // for(int i = 0; i<tokens.length;i++){
                //     System.out.println(tokens[i]);
                // }
                if (tokens.length == 3) {
                    users.add(new User(tokens[0], tokens[1], tokens[2]));
                }
                else if (tokens.length == 5) {
                    users.add(new User(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCommand() {
        return "registration";
    }

    @Override
    public String getCommandHelp() {
        return "Registers the user";
    }

    boolean isRegistered(String id) {
        //TODO
        for (int i = 0; i < users.size(); i++) { // cycle through the users to find the match who matches
            if (users.get(i).getID() != null && users.get(i).getID().equals(id)) // if ID exists and matches with the user sending command
                return true;
        }
        return false;
    }

    boolean registerUser (String userId, String registrationCode, Command command) {
        for (int i = 0; i < users.size(); i++) {
            if (!users.get(i).getRegistrationCode().equals("-") && users.get(i).getRegistrationCode().equals(registrationCode)) {   
                 if (users.get(i).getID() != null) {
                    return false;
                }
                users.get(i).setRegistrationCode("-");
                users.get(i).setID(userId);
                users.get(i).setUsername(command.getName());
                return true;
            }
        }
        return false;
    }

    @Override
    public String respond(Command command) {  ///////////edit the array list, then write back to file
        String userId = command.getSenderID();
        String registrationCode = command.getOption(0);

        if (isRegistered(userId)) {
            return "You are already registered!";
        }

        boolean registrationSuccess = registerUser(userId, registrationCode, command);

        if (registrationSuccess) {
            updateFile(); // Save the data to a file after successful registration
            return "You are registered!";
        } else {
            return "Invalid registration code!";
        }
    }
// write back to file to prevent data loss
    private void updateFile() {
        try {
            PrintWriter fileWriter = new PrintWriter(inputFile);
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i).toString() + "\n");
                fileWriter.print(users.get(i).toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * return the student ID of the user with the given discord ID
     *
     * throws an IDNotFoundException if the discord ID cannot be found
     */
    public String getStudentID(String id) throws IDNotFoundException {
        //TODO
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() != null && users.get(i).getID().equals(id))
                return users.get(i).getStudentID();
        }
        throw new IDNotFoundException("Discord ID cannot be found.");
    }

    /**
     * return the user object with the given discord ID
     *
     * throws an IDNotFoundException if the discord ID cannot be found
     */
    public User getStudent(String id) throws IDNotFoundException {
        //TODO
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID() != null && users.get(i).getID().equals(id))
                return users.get(i);
        }
        throw new IDNotFoundException("Discord ID cannot be found.");
    }


    /**
     * Output how many number of users have registered.
     */
    @Override
    public void actionPerform() {
        int registeredUserCount = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRegistrationCode().equals("-"))
                registeredUserCount++;
        }
        System.out.println("The number of registered users: " + registeredUserCount);
    }
}
