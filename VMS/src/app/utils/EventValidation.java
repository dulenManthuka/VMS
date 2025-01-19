package app.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventValidation {

    // Validate if a string is not empty
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate if the date is in the correct format (dd/MM/yyyy in this case)
    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Set lenient to false to enforce strict date parsing
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Validate if the event name is between a specific length (e.g., 3-50 characters)
    public static boolean isValidLength(String input, int minLength, int maxLength) {
        return input != null && input.length() >= minLength && input.length() <= maxLength;
    }
}