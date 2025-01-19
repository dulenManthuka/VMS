package app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VolunteerValidation {

    // Validate if a string is not empty
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Validate if the first name is between 1-50 characters
    public static boolean isValidFirstName(String firstName) {
        return isValidLength(firstName, 1, 50);
    }

    // Validate if the last name is between 1-50 characters
    public static boolean isValidLastName(String lastName) {
        return isValidLength(lastName, 1, 50);
    }

    // Validate if the email is in a correct format
    public static boolean isValidEmail(String email) {
        if (isNotEmpty(email)) {
            String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$"; // Simple regex for email validation
            Pattern pattern = Pattern.compile(emailRegex);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    // Validate if the username is between 3-30 characters and alphanumeric
    public static boolean isValidUsername(String username) {
        return isValidLength(username, 3, 30) && username.matches("^[a-zA-Z0-9]*$");
    }

    // Validate if the address is not empty
    public static boolean isValidAddress(String address) {
        return isNotEmpty(address);
    }

    // Validate if the password meets criteria
    public static boolean isValidPassword(String password) {
        if (isNotEmpty(password)) {
            // Password must be at least 8 characters long and contain:
            // at least one uppercase letter, one lowercase letter, one number, and one special character
            String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
            Pattern pattern = Pattern.compile(passwordRegex);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
        return false;
    }

    // Validate if the length is within specified bounds
    private static boolean isValidLength(String input, int minLength, int maxLength) {
        return input != null && input.length() >= minLength && input.length() <= maxLength;
    }
}
