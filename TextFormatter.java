// Strategy Pattern is used here since different text formatting strategies can be applied
// The TextFormatter class can be used to format text in different ways by implementing different formatting strategies.
// Needs a Formatter for Plain Text, HTML, and Markdown
public class TextFormatter {
    // The strategy interface for formatting text
    public interface Formatter {
        String format(String text);
    }
    
    // The concrete strategy for plain text formatting
    public static class PlainTextFormatter implements Formatter {
        @Override
        public String format(String text) {
            return text;
        }
    }
}
