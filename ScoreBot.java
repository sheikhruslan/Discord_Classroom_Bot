package bot;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import tools.*;

public class ScoreBot extends CommandBot {
    public static final String DEFAULT_FILE = "dictation.csv";

    ArrayList<String[]> scores;
    UserManagementBot umBot;
    String userName;
    public ScoreBot(UserManagementBot r) {
        umBot = r;
        File inputFile = new File(DEFAULT_FILE);
        scores = new ArrayList<>();
        try {
            try (Scanner fileReader = new Scanner(inputFile)) {
                while (fileReader.hasNextLine()) {
                    String scoreString = fileReader.nextLine();
                    String[] scoreList = scoreString.split(",");
                    scores.add(scoreList);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ScoreBot(UserManagementBot r, String filename) {
        umBot = r;
        File inputFile = new File(filename);
        scores = new ArrayList<>();
        try {
            try (Scanner fileReader = new Scanner(inputFile)) {
                while (fileReader.hasNextLine()) {
                    String scoresString = fileReader.nextLine();
                    String[] scoresList = scoresString.split(",");
                    scores.add(scoresList);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCommand() {
        return "score";
    }

    @Override
    public String getCommandHelp() {
        return "Returns the score of the user";
    }
    
 @Override
public String respond(Command command) throws IDNotFoundException {
    String result = "";
    String senderID = command.getSenderID();
    String commandText = "/"+ getCommand();

    if (commandText.startsWith("/registration")) {
        String registrationCode = commandText.substring("/registration".length()).trim();

        boolean registrationSuccess = umBot.isRegistered(senderID);

        if (registrationSuccess) {
            result = "You have been successfully registered.";
        } else {
            result = "Invalid registration code. Please try again.";
        }
    } else if (commandText.equals("/whoami")) {
        if (umBot.isRegistered(senderID)) {
            result = "You are registered.";
        } else {
            result = "You are not registered.";
        }
    } else if (commandText.equals("/score")) {
        // Check if the user is registered
        if (umBot.isRegistered(senderID)) {
            // Get the user's scores
            String stuId = umBot.getStudentID(command.getSenderID());
            String[] userScores = getUserScores(stuId);

            if (userScores != null) {
                result = "Your scores are: ";
                double sum = 0;
                int scoreCount = 0;

                for (int i = 1; i < userScores.length; i++) {
                    if (!userScores[i].equals("-")) {
                        double score = Double.parseDouble((String) userScores[i]);
                        sum += score;
                        scoreCount++;
                        result += score + ", ";
                    } else {
                        result += "N/A, ";
                    }
                }

                double average = sum / (double) scoreCount;
                result += "and your average score is: " + average;
            } else {
                result = "You have no scores.";
            }
        } else {
            result = "You are not registered.";
        }
    } else {
        result = "Invalid command. Please try again.";
    }

    return result;
}
    private String[] getUserScores(String studentId) {
        for(int i = 0; i < scores.size(); i++) {
            if(scores.get(i)[0].equals(studentId)) {
                return scores.get(i);
            }
        }
        return null;
    }
    @Override
    public void actionPerform() {
        //TODO
        System.out.println("The last user who queried the score bot:" + userName);
    }
}