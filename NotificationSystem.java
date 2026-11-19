//Going to use a decorator pattern to implement the notification system //
// Will need to create a concrete implementaion for each type of notification (email. SMS, push notification))

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
class SMSNotification extends NotificationSystem {
    @Override 
    public void sendNotification(String message){
        System.out.println("Sending SMS notification: " + message);
    }
}

// Concrete implementation for Slack notification //
class SlackNotification extends NotificationSystem {
    @Override 
    public void sendNotification(String message){
        System.out.println("Sending Slack notification: " + message);
    }
}