// Going to use a decorator pattern to implement the notification system //
// Will need to create a concrete implementaion for each type of notification (email. SMS, push notification))

// To create a new notification type, simply create a new class that extends 
// the NotificationDecorator class and implements the sendNotification method.

import java.util.ArrayList;
import java.util.List;

// NotificationManager class that manages the notification system
class NotificationManager{
    private boolean isEmailEnabled = false;
    private boolean isSMSEnabled = false;
    private boolean isSlackEnabled = false;
    
    // List to keep track of the notification history(to be added in the future)
    private final List<String> history = new ArrayList<>();

    // Methods to enable different types of notifications
    public void enableEmailNotification() {
        isEmailEnabled = true;
    }

    public void enableSMSNotification() {
        isSMSEnabled = true;
    }

    public void enableSlackNotification() {
        isSlackEnabled = true;
    }

    // Method to disable different types of notifications
    public void disableEmailNotification() {
        isEmailEnabled = false;
    }

    public void disableSMSNotification() {
        isSMSEnabled = false;
    }

    public void disableSlackNotification() {
        isSlackEnabled = false;
    }

    // Method to send notification based on the enabled notification types
    // format follows: "Sending basic notification: <message>" followed by the enabled notification types
    public void sendNotification(String message) {
        NotificationSystem notificationSystem = new BasicNotification();
        List<String> enabledNotifications = new ArrayList<>();
        // Add the basic notification to the list of enabled notifications
        enabledNotifications.add("Basic");

        if (isEmailEnabled) {
            notificationSystem = new EmailNotification(notificationSystem);
            enabledNotifications.add("Email");
        }

        if (isSMSEnabled) {
            notificationSystem = new SMSNotification(notificationSystem);
            enabledNotifications.add("SMS");
        }

        if (isSlackEnabled) {
            notificationSystem = new SlackNotification(notificationSystem);
            enabledNotifications.add("Slack");
        }

        notificationSystem.sendNotification(message);
        history.add(message);
    }

}

// Abstract class for the notification system //
public abstract class NotificationSystem {
    public abstract void sendNotification(String message);
}

// Concrete implementation for basic notification //
class BasicNotification extends NotificationSystem {
    @Override 
    public void sendNotification(String message){
        System.out.println("Sending basic notification: " + message);
    }
}
// Abstract decorator class for the notification system //
// Wraps a notification system and adds additional functionality //
abstract class NotificationDecorator extends NotificationSystem {
    protected NotificationSystem notificationSystem;

    public NotificationDecorator(NotificationSystem notificationSystem) {
        this.notificationSystem = notificationSystem;
    }

    @Override
    public void sendNotification(String message) {
        notificationSystem.sendNotification(message);
    }
}

// Concrete implementation for email notification //
class EmailNotification extends NotificationDecorator {
    public EmailNotification(NotificationSystem notificationSystem) {
        super(notificationSystem);
    }

    @Override
    public void sendNotification(String message) {
        // Call the base notification system's sendNotification method
        super.sendNotification(message);
        System.out.println("Sending email notification: " + message);
    }
}

// Concrete implementation for SMS notification //
class SMSNotification extends NotificationDecorator {
    public SMSNotification(NotificationSystem notificationSystem) {
        super(notificationSystem);
    }

    @Override
    public void sendNotification(String message) {
        // Call the base notification system's sendNotification method
        super.sendNotification(message);
        System.out.println("Sending SMS notification: " + message);
    }
}

// Concrete implementation for Slack notification //
class SlackNotification extends NotificationDecorator {
    public SlackNotification(NotificationSystem notificationSystem) {
        super(notificationSystem);
    }

    @Override
    public void sendNotification(String message) {
        // Call the base notification system's sendNotification method
        super.sendNotification(message);
        System.out.println("Sending Slack notification: " + message);
    }
}