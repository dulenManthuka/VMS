package app.view;

import app.controller.LoginController;
import app.utils.loginValidation;
import app.view.components.RoundedButtonUI;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel usernameErrorLabel;
    private JLabel passwordErrorLabel;
    private LoginController loginController;
    private String role;

    private String role_2;

    public LoginFrame(String role) {
        this.role = role;
        loginController = new LoginController();

        setTitle(role + " Login");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Username Label and TextField
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        styleInputField(usernameField);
        panel.add(usernameField);

        // Error Label for Username
        usernameErrorLabel = createErrorLabel();
        panel.add(usernameErrorLabel);

        // Password Label and PasswordField
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        styleInputField(passwordField);
        panel.add(passwordField);

        // Error Label for Password
        passwordErrorLabel = createErrorLabel();
        panel.add(passwordErrorLabel);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Login Button
        JButton loginButton = new JButton("Login as " + role);
        styleButton(loginButton);
        loginButton.addActionListener(e -> performLogin());
        panel.add(loginButton);

        getContentPane().add(panel);
    }

    private JLabel createErrorLabel() {
        JLabel label = new JLabel();
        label.setForeground(Color.RED); // Red text for error messages
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        return label;
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate inputs
        String usernameError = loginValidation.validateUsername(username);
        String passwordError = loginValidation.validatePassword(password);

        // Display error messages or clear them
        usernameErrorLabel.setText(usernameError != null ? usernameError : "");
        passwordErrorLabel.setText(passwordError != null ? passwordError : "");

        // If no validation errors, proceed with login
        if (usernameError == null && passwordError == null) {
            boolean success = loginController.login(username, password, role);
            if (success) {
                JOptionPane.showMessageDialog(this, role + " login successful!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                if ("Admin".equals(role)) {
                    new AdminDashboardFrame().setVisible(true);
                } else {
                    new VolunteerDashboardFrame().setVisible(true);
                }
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        button.setBackground(new Color(0x1B4F72));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setUI(new RoundedButtonUI(new Color(0x1B4F72), new Color(0x154360)));
    }

    private void styleInputField(JTextField field) {
        field.setMaximumSize(new Dimension(300, 30));
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x1B4F72), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        field.setBackground(new Color(0xF0F0F0));
    }
}
