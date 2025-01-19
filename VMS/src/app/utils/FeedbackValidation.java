package app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FeedbackValidation {

    // Validate if a string is not empty
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate if the feedback message is within a specific length (e.g., 5-500 characters)
    public static boolean isValidLength(String input, int minLength, int maxLength) {
        return input != null && input.length() >= minLength && input.length() <= maxLength;
    }

    // Validate if the feedback date is in the correct format (dd/MM/yyyy)
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
}
