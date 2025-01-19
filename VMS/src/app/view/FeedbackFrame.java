package app.view;

import app.controller.FeedbackController;
import app.model.Feedback;
import app.model.Volunteer;
import app.utils.FeedbackValidation;
import app.view.components.RoundedButtonUI;
import app.view.VolunteerDashboardFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

    public class FeedbackFrame extends JFrame {
        private JTextField feedbackIdField;
        private JTextField messageField;
        private JTextField dateField;
        private JTable feedbackTable;
        private FeedbackController controller;
        private int selectedFeedbackId = -1;
        private JButton backButton;

        // Labels for validation error messages
        private JLabel messageErrorLabel;
        private JLabel dateErrorLabel;

        public FeedbackFrame() {
            controller = new FeedbackController();

            setTitle("Feedback Management");
            setSize(1100, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            JPanel leftPanel = new JPanel(new GridBagLayout());
            leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            leftPanel.setBackground(Color.WHITE);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

           /* // Feedback ID (Read-only, for display)
            gbc.gridx = 0;
            gbc.gridy = 0;
            leftPanel.add(new JLabel("Feedback ID:"), gbc);*/

            backButton = new JButton("Back");
            // Optionally style the button
            styleButton(backButton); // Reuse the styling method if you have one
            backButton.addActionListener(e -> navigateBack()); // Define navigation back or logout logic
            gbc.gridx = 0; // Column 0 for the button
            gbc.gridy = 0; // Row 0 for the button
            leftPanel.add(backButton, gbc);


            gbc.gridx = 0;
            gbc.gridy = 1;
            feedbackIdField = new JTextField(20);
            feedbackIdField.setEditable(false); // Make the ID field read-only
            //leftPanel.add(feedbackIdField, gbc);

            // Message input
            gbc.gridx = 0;
            gbc.gridy = 2;
            leftPanel.add(new JLabel("Message:"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            messageField = new JTextField(20);
            leftPanel.add(messageField, gbc);

            // Validation message for message
            gbc.gridx = 0;
            gbc.gridy = 4;
            messageErrorLabel = new JLabel("");
            messageErrorLabel.setForeground(Color.RED);
            leftPanel.add(messageErrorLabel, gbc);

            // Date input
            gbc.gridx = 0;
            gbc.gridy = 5;
            leftPanel.add(new JLabel("Date (DD/MM/YYYY):"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            dateField = new JTextField(20);
            leftPanel.add(dateField, gbc);

            // Validation message for date
            gbc.gridx = 0;
            gbc.gridy = 7;
            dateErrorLabel = new JLabel("");
            dateErrorLabel.setForeground(Color.RED);
            leftPanel.add(dateErrorLabel, gbc);

            // Buttons for feedback operations
            gbc.gridy = 8;
            gbc.gridx = 0;
            gbc.gridwidth = 2;

            JButton addButton = new JButton("Add Feedback");
            styleButton(addButton);
            addButton.addActionListener(e -> addFeedback());
            leftPanel.add(addButton, gbc);

            gbc.gridy = 9;
            JButton updateButton = new JButton("Update Feedback");
            styleButton(updateButton);
            updateButton.addActionListener(e -> updateFeedback());
            leftPanel.add(updateButton, gbc);

            gbc.gridy = 10;
            JButton deleteButton = new JButton("Delete Feedback");
            styleButton(deleteButton);
            deleteButton.addActionListener(e -> deleteFeedback());
            leftPanel.add(deleteButton, gbc);

            // Add the left panel to the frame
            getContentPane().add(leftPanel, BorderLayout.WEST);

            // Right panel for table and feedback operations
            JPanel rightPanel = new JPanel(new BorderLayout());
            rightPanel.setBackground(Color.WHITE);

            // Table for displaying feedback
            feedbackTable = new JTable();
            JScrollPane scrollPane = new JScrollPane(feedbackTable);
            feedbackTable.getSelectionModel().addListSelectionListener(e -> selectFeedback());
            rightPanel.add(scrollPane, BorderLayout.CENTER);

            displayFeedback();

            // Add the right panel to the frame
            getContentPane().add(rightPanel, BorderLayout.CENTER);
        }

        // Add Feedback Method
        private void addFeedback() {
            clearErrorLabels();
            String message = messageField.getText();
            String date = dateField.getText();

            if (!validateInputs(message, date)) {
                return; // Do not proceed if validation fails
            }

            controller.addFeedback(new Feedback(0, message, date));
            displayFeedback();
            clearInputFields();
        }

        // Update Feedback Method
        private void updateFeedback() {
            if (selectedFeedbackId != -1) {
                clearErrorLabels();
                String message = messageField.getText();
                String date = dateField.getText();

                if (!validateInputs(message, date)) {
                    return; // Do not proceed if validation fails
                }

                controller.updateFeedback(new Feedback(selectedFeedbackId, message, date));
                displayFeedback();
                clearInputFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a feedback to update", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Delete Feedback Method
        private void deleteFeedback() {
            if (selectedFeedbackId != -1) {
                controller.deleteFeedback(selectedFeedbackId);
                displayFeedback();
                clearInputFields();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a feedback to delete", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Select Feedback from Table
        private void selectFeedback() {
            int selectedRow = feedbackTable.getSelectedRow();
            if (selectedRow != -1) {
                selectedFeedbackId = Integer.parseInt(feedbackTable.getValueAt(selectedRow, 0).toString());
                feedbackIdField.setText(String.valueOf(selectedFeedbackId));
                messageField.setText(feedbackTable.getValueAt(selectedRow, 1).toString());
                dateField.setText(feedbackTable.getValueAt(selectedRow, 2).toString());
            } else {
                clearInputFields();
            }
        }

        // Display Feedback in the Table
        private void displayFeedback() {
            List<Feedback> feedbacks = controller.getAllFeedbacks();
            String[] columnNames = {"F_ID", "Message", "Date"};
            Object[][] data = new Object[feedbacks.size()][3];

            for (int i = 0; i < feedbacks.size(); i++) {
                data[i][0] = feedbacks.get(i).getF_ID();
                data[i][1] = feedbacks.get(i).getMessage();
                data[i][2] = feedbacks.get(i).getFeedbackDate();
            }
            feedbackTable.setModel(new DefaultTableModel(data, columnNames));
        }

        // Validation Methods
        private boolean validateInputs(String message, String date) {
            boolean isValid = true;

            if (!FeedbackValidation.isNotEmpty(message)) {
                messageErrorLabel.setText("Message is required.");
                isValid = false;
            } else if (!FeedbackValidation.isValidLength(message, 10, 200)) {
                messageErrorLabel.setText("Message must be between 10 and 200 characters.");
                isValid = false;
            }

            if (!FeedbackValidation.isValidDate(date)) {
                dateErrorLabel.setText("Please enter a valid date in the format dd/MM/yyyy.");
                isValid = false;
            }

            return isValid;
        }

        private void navigateBack() {
            // Close this frame
            dispose();
            // Here you can create and show the Admin Dashboard frame.
            VolunteerDashboardFrame volunteerDashboard = new VolunteerDashboardFrame(); // Create the admin dashboard frame class
            volunteerDashboard.setVisible(true); // Show the admin dashboard
        }


        private void clearErrorLabels() {
            messageErrorLabel.setText("");
            dateErrorLabel.setText("");
        }

        private void clearInputFields() {
            feedbackIdField.setText("");
            messageField.setText("");
            dateField.setText("");
            selectedFeedbackId = -1;
        }

        private void styleButton(JButton button) {
            button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            button.setUI(new RoundedButtonUI(new Color(0x154360), new Color(0x1B4F72)));
        }
    }




