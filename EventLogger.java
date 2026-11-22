// Needs to implament a Singleton pattern to ensure only one instance of the EventLogger exists throughout the application.
// Needs to append the log messages to a log file.
// Needs to provide a method to retrieve the log messages from the log file.
// Needs to provide a method to clear the log messages from the log file.
// Needs synchronized instance to prevent multithreading.

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
    // Added synchronized here to prevent two instances being created
    public static synchronized EventLogger getInstance(){
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
    // Added synchronized here to prevent two writing attempts happening simultaneously
    public synchronized void logMessages(String message){
        String time;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logMessages.log", true))){
            time = time();
            writer.write(time + " ");
            writer.write(message);
            writer.newLine();
            //added to write to console
            System.out.println(time + " " + message);
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to read logMessages
    // Added synchronized here to prevent two getting attempts happening simultaneously
    public synchronized String getLogMessages(){      
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
    // Added synchronized here to prevent two clearing attempts happening simultaneously
    public synchronized void clearLogMessages(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("logMessages.log", false))){
            writer.write("");
        }catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to archive logMeassages
    public synchronized void archiveLogMessages(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("archivedLogMessages.log", true))){
            writer.append(getLogMessages());
            clearLogMessages();
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
