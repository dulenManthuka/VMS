package app.controller;

import app.dao.UserDAO;

public class LoginController {
    private UserDAO userDAO;

    public LoginController() {
        userDAO = new UserDAO();
    }

    public boolean login(String username, String password, String role) {
        // Authenticate user from the database
        return userDAO.authenticateUser(username, password, role);
    }
}