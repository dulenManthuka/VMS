package app.utils;

import java.util.regex.Pattern;

public class DonationValidation {

    // Validate Donor Name (must not be empty and between 2 to 100 characters)
    public static boolean isValidDonorName(String donorName) {
        return donorName != null && donorName.trim().length() >= 2 && donorName.trim().length() <= 100;
    }

    // Validate Email (simple regex check for format)
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(emailRegex, email);
    }

    // Validate Description (must not be empty and between 5 to 500 characters)
    public static boolean isValidDescription(String description) {
        return description != null && description.trim().length() >= 5 && description.trim().length() <= 500;
    }

    // Validate Reference Number (must not be empty and between 3 to 50 characters)
    public static boolean isValidReferenceNumber(String referenceNumber) {
        return referenceNumber != null && referenceNumber.trim().length() >= 3 && referenceNumber.trim().length() <= 50;
    }

    // Validate Donation Date (must match the format dd/MM/yyyy)
    public static boolean isValidDate(String date) {
        String dateRegex = "^\\d{2}/\\d{2}/\\d{4}$";
        return date != null && Pattern.matches(dateRegex, date);
    }

    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

}
