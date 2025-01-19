package app.utils;

public class loginValidation {
    public static String validateUsername(String username) {
        if (username == null || username.isEmpty()) {
            return "Username is required.";
        }
        if (username.length() < 5) {
            return "Username must be at least 5 characters.";
        }
        return null;
    }

    public static String validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password is required.";
        }
        if (password.length() < 6) {
            return "Password must be at least 6 characters.";
        }
        return null;
    }
}