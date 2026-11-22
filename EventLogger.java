// Needs to implament a Singleton pattern to ensure only one instance of the EventLogger exists throughout the application.
// Needs to append the log messages to a log file.
// Needs to provide a method to retrieve the log messages from the log file.
// Needs to provide a method to clear the log messages from the log file.

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;

public class EventLogger{
    // Creates single Instance
    private static EventLogger instance;

    // Creates private constructor
    private EventLogger(){}

    // Creates getInstance
    public static EventLogger getInstance(){
        if (instance == null){
            instance = new EventLogger();
        }return instance;
    }

    // Method to log messages
    public void logMessages(String message){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logMessages.log", true))){
            writer.write(message);
            writer.newLine();
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to read logMessages
    public String getLogMessages(){      
        StringBuilder history = new StringBuilder("");
        try (BufferedReader reader = new BufferedReader(new FileReader("logMessages.log"))) {
            String line;
            while((line =reader.readLine()) != null){
                history.append(line);
                history.append(System.lineSeparator());
            }
        } catch (IOException e) {
          System.out.println("Error: " + e.getMessage());
        }
        return history.toString();
    }
}
