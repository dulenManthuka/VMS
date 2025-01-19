package app.view;

import app.controller.VolunteerController;
import app.model.Volunteer;
import app.utils.VolunteerValidation;
import app.view.components.RoundedButtonUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VolunteerManagementFrame extends JFrame {
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField usernameField;
    private JTextField addressField;
    private JTextField skillsField; // Skills field
    private JPasswordField passwordField; // Password field
    private JTable volunteerTable;
    private VolunteerController controller;
    private int selectedVolunteerId = -1;

    // Labels for validation error messages
    private JLabel firstNameErrorLabel;
    private JLabel lastNameErrorLabel;
    private JLabel emailErrorLabel;
    private JLabel usernameErrorLabel;
    private JLabel skillsErrorLabel; // Skills error label
    private JLabel passwordErrorLabel; // Password error label
    private JButton backButton;

    // Radio buttons for status
    private JRadioButton activeRadioButton;
    private JRadioButton inactiveRadioButton;

    public VolunteerManagementFrame() {
        controller = new VolunteerController();

        setTitle("Volunteer Management");
        setSize(1300, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the logout/back button
        backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> navigateBack());
        gbc.gridx = 0; // Column 0 for the button
        gbc.gridy = 0; // Row 0 for the button
        leftPanel.add(backButton, gbc);

        // First Name input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 1; // Row 1 for the label
        leftPanel.add(new JLabel("First Name:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 2; // Row 2 for the text field
        firstNameField = new JTextField(20);
        leftPanel.add(firstNameField, gbc);

        // Validation message for first name
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 3; // Row 3 for the validation message
        firstNameErrorLabel = new JLabel("");
        firstNameErrorLabel.setForeground(Color.RED);
        leftPanel.add(firstNameErrorLabel, gbc);

        // Last Name input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 4; // Row 4 for the label
        leftPanel.add(new JLabel("Last Name:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 5; // Row 5 for the text field
        lastNameField = new JTextField(20);
        leftPanel.add(lastNameField, gbc);

        // Validation message for last name
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 6; // Row 6 for the validation message
        lastNameErrorLabel = new JLabel("");
        lastNameErrorLabel.setForeground(Color.RED);
        leftPanel.add(lastNameErrorLabel, gbc);

        // Email input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 7; // Row 7 for the label
        leftPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 8; // Row 8 for the text field
        emailField = new JTextField(20);
        leftPanel.add(emailField, gbc);

        // Validation message for email
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 9; // Row 9 for the validation message
        emailErrorLabel = new JLabel("");
        emailErrorLabel.setForeground(Color.RED);
        leftPanel.add(emailErrorLabel, gbc);

        // Username input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 10; // Row 10 for the label
        leftPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 11; // Row 11 for the text field
        usernameField = new JTextField(20);
        leftPanel.add(usernameField, gbc);

        // Validation message for username
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 12; // Row 12 for the validation message
        usernameErrorLabel = new JLabel("");
        usernameErrorLabel.setForeground(Color.RED);
        leftPanel.add(usernameErrorLabel, gbc);

        // Password input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 13; // Row 13 for the label
        leftPanel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 0; // Column 0 for the password field
        gbc.gridy = 14; // Row 14 for the password field
        passwordField = new JPasswordField(20);
        leftPanel.add(passwordField, gbc);

        // Validation message for password
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 15; // Row 15 for the validation message
        passwordErrorLabel = new JLabel("");
        passwordErrorLabel.setForeground(Color.RED);
        leftPanel.add(passwordErrorLabel, gbc);

        // Address input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 16; // Row 16 for the label
        leftPanel.add(new JLabel("Address:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 17; // Row 17 for the text field
        addressField = new JTextField(20);
        leftPanel.add(addressField, gbc);

        // Skills input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 18; // Row 18 for the label
        leftPanel.add(new JLabel("Skills:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 19; // Row 19 for the text field
        skillsField = new JTextField(20);
        leftPanel.add(skillsField, gbc);

        // Validation message for skills
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 20; // Row 20 for the validation message
        skillsErrorLabel = new JLabel("");
        skillsErrorLabel.setForeground(Color.RED);
        leftPanel.add(skillsErrorLabel, gbc);

        // Status radio buttons
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 21; // Row 21 for the label
        leftPanel.add(new JLabel("Status:"), gbc);

        activeRadioButton = new JRadioButton("Active");
        inactiveRadioButton = new JRadioButton("Inactive");

        // Group the radio buttons
        ButtonGroup statusGroup = new ButtonGroup();
        statusGroup.add(activeRadioButton);
        statusGroup.add(inactiveRadioButton);
        activeRadioButton.setSelected(true); // Set default to Active

        // Panel for radio buttons
        JPanel statusPanel = new JPanel();
        statusPanel.add(activeRadioButton);
        statusPanel.add(inactiveRadioButton);
        gbc.gridx = 0; // Column 0 for the radio button panel
        gbc.gridy = 22; // Row 22 for the radio button panel
        leftPanel.add(statusPanel, gbc);

        // Buttons for volunteer operations
        gbc.gridy = 23; // Row 23 for the first button
        gbc.gridx = 0; // Column 0 for the button
        gbc.gridwidth = 2; // Make buttons span two columns

        JButton addButton = new JButton("Add Volunteer");
        styleButton(addButton);
        addButton.addActionListener(e -> addVolunteer());
        leftPanel.add(addButton, gbc);

        gbc.gridy = 24; // Row 24 for the second button
        JButton updateButton = new JButton("Update Volunteer");
        styleButton(updateButton);
        updateButton.addActionListener(e -> updateVolunteer());
        leftPanel.add(updateButton, gbc);

        gbc.gridy = 25; // Row 25 for the third button
        JButton deleteButton = new JButton("Delete Volunteer");
        styleButton(deleteButton);
        deleteButton.addActionListener(e -> deleteVolunteer());
        leftPanel.add(deleteButton, gbc);

        // Add the left panel to the frame
        getContentPane().add(leftPanel, BorderLayout.WEST);

        // Right panel for table and volunteer operations
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        volunteerTable = new JTable();
        volunteerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        volunteerTable.getSelectionModel().addListSelectionListener(e -> selectVolunteer());
        rightPanel.add(new JScrollPane(volunteerTable), BorderLayout.CENTER);
        getContentPane().add(rightPanel, BorderLayout.CENTER);

        displayVolunteers(); // Initial display
    }

    // Add Volunteer Method
    private void addVolunteer() {
        clearErrorLabels(); // Clear previous errors
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // Get password input
        String address = addressField.getText();
        String skills = skillsField.getText(); // Get skills input
        String status = activeRadioButton.isSelected() ? "Active" : "Inactive"; // Get selected status

        // Validate input fields
        boolean isValid = validateFields(firstName, lastName, email, username, password, skills);
        if (isValid) {
            Volunteer volunteer = new Volunteer(selectedVolunteerId, firstName, lastName, email, username, password, address, status, skills);
            controller.addVolunteer(volunteer);
            displayVolunteers();
            clearFields();
        }
    }

    // Update Volunteer Method
    private void updateVolunteer() {
        if (selectedVolunteerId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a volunteer to update.");
            return;
        }
        clearErrorLabels(); // Clear previous errors
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword()); // Get password input
        String address = addressField.getText();
        String skills = skillsField.getText(); // Get skills input
        String status = activeRadioButton.isSelected() ? "Active" : "Inactive"; // Get selected status

        // Validate input fields
        boolean isValid = validateFields(firstName, lastName, email, username, password, skills);
        if (isValid) {
            Volunteer volunteer = new Volunteer(selectedVolunteerId, firstName, lastName, email, username, password, address, status, skills);
            controller.updateVolunteer(volunteer);
            displayVolunteers();
            clearFields();
            selectedVolunteerId = -1;
        }
    }

    // Delete Volunteer Method
    private void deleteVolunteer() {
        if (selectedVolunteerId == -1) {
            JOptionPane.showMessageDialog(this, "Please select a volunteer to delete.");
            return;
        }
        controller.deleteVolunteer(selectedVolunteerId);
        displayVolunteers();
        clearFields();
        selectedVolunteerId = -1;
    }

    // Validate input fields
    private boolean validateFields(String firstName, String lastName, String email, String username, String password, String skills) {
        boolean isValid = true;
        if (!VolunteerValidation.isValidFirstName(firstName)) {
            firstNameErrorLabel.setText("Invalid first name.");
            isValid = false;
        }
        if (!VolunteerValidation.isValidLastName(lastName)) {
            lastNameErrorLabel.setText("Invalid last name.");
            isValid = false;
        }
        if (!VolunteerValidation.isValidEmail(email)) {
            emailErrorLabel.setText("Invalid email.");
            isValid = false;
        }
        if (!VolunteerValidation.isValidUsername(username)) {
            usernameErrorLabel.setText("Invalid username.");
            isValid = false;
        }
        if (!VolunteerValidation.isValidPassword(password)) {
            passwordErrorLabel.setText("Password must be strong.");
            isValid = false;
        }
        return isValid;
    }

    // Clear fields and error labels
    private void clearFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText(""); // Clear password field
        addressField.setText("");
        skillsField.setText("");
        activeRadioButton.setSelected(true); // Reset to default
        clearErrorLabels();
    }

    // Clear error labels
    private void clearErrorLabels() {
        firstNameErrorLabel.setText("");
        lastNameErrorLabel.setText("");
        emailErrorLabel.setText("");
        usernameErrorLabel.setText("");
        passwordErrorLabel.setText(""); // Clear password error label
        skillsErrorLabel.setText("");
    }

    // Display volunteers in the table
    private void displayVolunteers() {
        List<Volunteer> volunteers = controller.getAllVolunteers();
        String[] columnNames = {"ID", "First Name", "Last Name", "Email", "Username","Password", "Address", "Status", "Skills"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Volunteer volunteer : volunteers) {
            model.addRow(new Object[]{volunteer.getVolunteerId(), volunteer.getFirstName(), volunteer.getLastName(),
                    volunteer.getEmail(), volunteer.getUserName(), volunteer.getPassword() , volunteer.getAddress(), volunteer.getCurrentStatus(),
                    volunteer.getSkills()}); // Added skills to the table
        }
        volunteerTable.setModel(model);
    }

    // Select volunteer from the table
    private void selectVolunteer() {
        int selectedRow = volunteerTable.getSelectedRow();
        if (selectedRow != -1) {
            selectedVolunteerId = (int) volunteerTable.getValueAt(selectedRow, 0); // Assuming this is the volunteerId
            firstNameField.setText((String) volunteerTable.getValueAt(selectedRow, 1)); // First name
            lastNameField.setText((String) volunteerTable.getValueAt(selectedRow, 2)); // Last name
            emailField.setText((String) volunteerTable.getValueAt(selectedRow, 3)); // Email
            usernameField.setText((String) volunteerTable.getValueAt(selectedRow, 4)); // Username
            passwordField.setText((String) volunteerTable.getValueAt(selectedRow, 5));//Password
            addressField.setText((String) volunteerTable.getValueAt(selectedRow, 6)); // Address
            String status = (String) volunteerTable.getValueAt(selectedRow, 7); // Current status
            if ("Active".equals(status)) {
                activeRadioButton.setSelected(true);
            } else {
                inactiveRadioButton.setSelected(true);
            }
            skillsField.setText((String) volunteerTable.getValueAt(selectedRow, 8)); // Skills

        }
    }

    // Navigate back to previous screen
    private void navigateBack() {
        dispose();

        // Here you can create and show the Admin Dashboard frame.
        AdminDashboardFrame adminDashboard = new AdminDashboardFrame(); // Create the admin dashboard frame class
        adminDashboard.setVisible(true); // Show the admin dashboard
    }

    // Style button method
    private void styleButton(JButton button) {
        button.setUI(new RoundedButtonUI(new Color(0x154360), new Color(0x1B4F72)));
        button.setPreferredSize(new Dimension(150, 40));
    }
}
