package app.view;

import app.controller.EventController;
import app.model.Event;
import app.utils.EventValidation;
import app.view.components.RoundedButtonUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class EventManagementFrame extends JFrame {
    private JTextField nameField;
    private JTextField locationField;
    private JTextField dateField;
    private JTable eventTable;
    private EventController controller;
    private int selectedEventId = -1;

    // Labels for validation error messages
    private JLabel nameErrorLabel;
    private JLabel locationErrorLabel;
    private JLabel dateErrorLabel;
    private JButton backButton;

    public EventManagementFrame() {
        controller = new EventController();

        setTitle("Event Management");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

// Add the logout/back button
//        backButton = new JButton("Back");
        backButton = new JButton("Back");
// Optionally style the button
        styleButton(backButton); // Reuse the styling method if you have one
        backButton.addActionListener(e -> navigateBack()); // Define navigation back or logout logic
        gbc.gridx = 0; // Column 0 for the button
        gbc.gridy = 0; // Row 0 for the button
        leftPanel.add(backButton, gbc);

// Event Name input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 1; // Row 1 for the label (moved down after back button)
        leftPanel.add(new JLabel("Event Name:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 2; // Row 2 for the text field
        nameField = new JTextField(20);
        leftPanel.add(nameField, gbc);

// Validation message for name
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 3; // Row 3 for the validation message
        nameErrorLabel = new JLabel("");
        nameErrorLabel.setForeground(Color.RED);
        leftPanel.add(nameErrorLabel, gbc);

// Location input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 4; // Row 4 for the label
        leftPanel.add(new JLabel("Location:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 5; // Row 5 for the text field
        locationField = new JTextField(20);
        leftPanel.add(locationField, gbc);

// Validation message for location
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 6; // Row 6 for the validation message
        locationErrorLabel = new JLabel("");
        locationErrorLabel.setForeground(Color.RED);
        leftPanel.add(locationErrorLabel, gbc);

// Date input
        gbc.gridx = 0; // Column 0 for the label
        gbc.gridy = 7; // Row 7 for the label
        leftPanel.add(new JLabel("Date:"), gbc);

        gbc.gridx = 0; // Column 0 for the text field
        gbc.gridy = 8; // Row 8 for the text field
        dateField = new JTextField(20);
        leftPanel.add(dateField, gbc);

// Validation message for date
        gbc.gridx = 0; // Column 0 for the validation message
        gbc.gridy = 9; // Row 9 for the validation message
        dateErrorLabel = new JLabel("");
        dateErrorLabel.setForeground(Color.RED);
        leftPanel.add(dateErrorLabel, gbc);

// Buttons for event operations
        gbc.gridy = 10; // Row 10 for the first button
        gbc.gridx = 0; // Column 0 for the button
        gbc.gridwidth = 2; // Make buttons span two columns

        JButton addButton = new JButton("Add Event");
        styleButton(addButton);
        addButton.addActionListener(e -> {
            try {
                addEvent();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        leftPanel.add(addButton, gbc);

        gbc.gridy = 11; // Row 11 for the second button
        JButton updateButton = new JButton("Update Event");
        styleButton(updateButton);
        updateButton.addActionListener(e -> updateEvent());
        leftPanel.add(updateButton, gbc);

        gbc.gridy = 12; // Row 12 for the third button
        JButton deleteButton = new JButton("Delete Event");
        styleButton(deleteButton);
        deleteButton.addActionListener(e -> deleteEvent());
        leftPanel.add(deleteButton, gbc);

// Add the left panel to the frame
        getContentPane().add(leftPanel, BorderLayout.WEST);

// Right panel for table and event operations
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

// Table for displaying events
        eventTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(eventTable);
        eventTable.getSelectionModel().addListSelectionListener(e -> selectEvent());
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        displayEvents();

// Add the right panel to the frame
        getContentPane().add(rightPanel, BorderLayout.CENTER);

    }

    // Add Event Method
    private void addEvent() throws SQLException {
        clearErrorLabels(); // Clear previous errors
        String name = nameField.getText();
        String location = locationField.getText();
        String date = dateField.getText();

        if (!validateInputs(name, location, date)) {
            return; // Do not proceed if validation fails
        }

        if (!controller.isEventNameUnique(name)) {
            JOptionPane.showMessageDialog(this, "Event name already exists. Please choose a different name.", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop the method execution if the event name is not unique
        }

        controller.addEvent(new Event(0, name, location, date));
        displayEvents();
        clearInputFields();
    }

    // Update Event Method
    private void updateEvent() {
        if (selectedEventId != -1) {
            clearErrorLabels(); // Clear previous errors
            String name = nameField.getText();
            String location = locationField.getText();
            String date = dateField.getText();

            if (!validateInputs(name, location, date)) {
                return; // Do not proceed if validation fails
            }

            controller.updateEvent(new Event(selectedEventId, name, location, date));
            displayEvents();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Delete Event Method
    private void deleteEvent() {
        if (selectedEventId != -1) {
            controller.deleteEvent(selectedEventId);
            displayEvents();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Select Event from Table
    private void selectEvent() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow != -1) {
            selectedEventId = Integer.parseInt(eventTable.getValueAt(selectedRow, 0).toString());
            nameField.setText(eventTable.getValueAt(selectedRow, 1).toString());
            locationField.setText(eventTable.getValueAt(selectedRow, 2).toString());
            dateField.setText(eventTable.getValueAt(selectedRow, 3).toString());
        } else {
            // Clear input fields if no row is selected
            clearInputFields();
        }
    }

    // Display Events in the Table
    private void displayEvents() {
        List<Event> events = controller.getAllEvents();
        String[] columnNames = {"ID", "Name", "Location", "Date"}; // Include ID in the model
        Object[][] data = new Object[events.size()][4];

        for (int i = 0; i < events.size(); i++) {
            data[i][0] = events.get(i).getId(); // Store ID for selection
            data[i][1] = events.get(i).getName();
            data[i][2] = events.get(i).getLocation();
            data[i][3] = events.get(i).getDate();
        }
        eventTable.setModel(new DefaultTableModel(data, columnNames));
    }

    // Validation Methods
    private boolean validateInputs(String name, String location, String date) {
        boolean isValid = true;

        if (!EventValidation.isNotEmpty(name)) {
            nameErrorLabel.setText("Event Name is required.");
            isValid = false;
        } else if (!EventValidation.isValidLength(name, 3, 50)) {
            nameErrorLabel.setText("Event Name must be between 3 and 50 characters.");
            isValid = false;
        }

        if (!EventValidation.isNotEmpty(location)) {
            locationErrorLabel.setText("Location is required.");
            isValid = false;
        }

        if (!EventValidation.isValidDate(date)) {
            dateErrorLabel.setText("Please enter a valid date in the format dd/MM/yyyy.");
            isValid = false;
        }

        return isValid;  // Return true if all validations pass
    }

    private void clearErrorLabels() {
        nameErrorLabel.setText("");
        locationErrorLabel.setText("");
        dateErrorLabel.setText("");
    }

    private void clearInputFields() {
        nameField.setText("");
        locationField.setText("");
        dateField.setText("");
        selectedEventId = -1; // Reset selected event ID
    }

    private void navigateBack() {
        // Close this frame
        dispose();
        // Here you can create and show the Admin Dashboard frame.
        AdminDashboardFrame adminDashboard = new AdminDashboardFrame(); // Create the admin dashboard frame class
        adminDashboard.setVisible(true); // Show the admin dashboard
    }

    private void styleButton(JButton button) {
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setUI(new RoundedButtonUI(new Color(0x154360), new Color(0x1B4F72))); // Rounded button UI
    }
}
