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
