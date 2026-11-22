// Needs to implament a Singleton pattern to ensure only one instance of the EventLogger exists throughout the application.
// Needs to append the log messages to a log file.
// Needs to provide a method to retrieve the log messages from the log file.
// Needs to provide a method to clear the log messages from the log file.

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.time.LocalDateTime;

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

    // Method to get time//
    public String time(){
        LocalDateTime now = LocalDateTime.now();
        String currentTime = now.toString();
        return currentTime;
    }

    // Method to log messages and write to console 
    public void logMessages(String message){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logMessages.log", true))){
            writer.write(time() + " ");
            writer.write(message);
            writer.newLine();
            //added to write to console
            System.out.println(message);
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

    // Method to clear log file
    public void clearLogMessages(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logMessages.log", false))){
            writer.write("");
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
