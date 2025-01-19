package app.view;

import app.controller.DonationController;
import app.model.Donation;
import app.utils.DonationValidation;
import app.view.VolunteerDashboardFrame;
import app.view.components.RoundedButtonUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class DonationFrame extends JFrame {
    private JTextField donorNameField;
    private JTextField emailField;
    private JTextField descriptionField;
    private JTextField referenceNumberField;
    private JTextField dateField;
    private JTable donationTable;
    private DonationController controller;
    private int selectedDonationId = -1;
    private JButton backButton;

    // Labels for validation error messages
    private JLabel donorNameErrorLabel;
    private JLabel emailErrorLabel;
    private JLabel descriptionErrorLabel;
    private JLabel referenceNumberErrorLabel;
    private JLabel dateErrorLabel;

    public DonationFrame() {
        controller = new DonationController();

        setTitle("Donation Management");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        leftPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Back Button
        backButton = new JButton("Back");
        styleButton(backButton);
        backButton.addActionListener(e -> navigateBack());
        gbc.gridx = 0;
        gbc.gridy = 0;
        leftPanel.add(backButton, gbc);

        // Donor Name input
        gbc.gridx = 0;
        gbc.gridy = 1;
        leftPanel.add(new JLabel("Donor Name:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        donorNameField = new JTextField(20);
        leftPanel.add(donorNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        donorNameErrorLabel = new JLabel("");
        donorNameErrorLabel.setForeground(Color.RED);
        leftPanel.add(donorNameErrorLabel, gbc);

        // Email input
        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        emailField = new JTextField(20);
        leftPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        emailErrorLabel = new JLabel("");
        emailErrorLabel.setForeground(Color.RED);
        leftPanel.add(emailErrorLabel, gbc);

        // Description input
        gbc.gridx = 0;
        gbc.gridy = 7;
        leftPanel.add(new JLabel("Description:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        descriptionField = new JTextField(20);
        leftPanel.add(descriptionField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        descriptionErrorLabel = new JLabel("");
        descriptionErrorLabel.setForeground(Color.RED);
        leftPanel.add(descriptionErrorLabel, gbc);

        // Reference Number input
        gbc.gridx = 0;
        gbc.gridy = 10;
        leftPanel.add(new JLabel("Reference Number:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        referenceNumberField = new JTextField(20);
        leftPanel.add(referenceNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 12;
        referenceNumberErrorLabel = new JLabel("");
        referenceNumberErrorLabel.setForeground(Color.RED);
        leftPanel.add(referenceNumberErrorLabel, gbc);

        // Date input
        gbc.gridx = 0;
        gbc.gridy = 13;
        leftPanel.add(new JLabel("Date (DD/MM/YYYY):"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 14;
        dateField = new JTextField(20);
        leftPanel.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 15;
        dateErrorLabel = new JLabel("");
        dateErrorLabel.setForeground(Color.RED);
        leftPanel.add(dateErrorLabel, gbc);

        // Buttons for donation operations
        gbc.gridy = 16;
        JButton addButton = new JButton("Add Donation");
        styleButton(addButton);
        addButton.addActionListener(e -> addDonation());
        leftPanel.add(addButton, gbc);

        gbc.gridy = 17;
        JButton updateButton = new JButton("Update Donation");
        styleButton(updateButton);
        updateButton.addActionListener(e -> updateDonation());
        leftPanel.add(updateButton, gbc);

        gbc.gridy = 18;
        JButton deleteButton = new JButton("Delete Donation");
        styleButton(deleteButton);
        deleteButton.addActionListener(e -> deleteDonation());
        leftPanel.add(deleteButton, gbc);

        getContentPane().add(leftPanel, BorderLayout.WEST);

        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE);

        donationTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(donationTable);
        donationTable.getSelectionModel().addListSelectionListener(e -> selectDonation());
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        displayDonations();

        getContentPane().add(rightPanel, BorderLayout.CENTER);
    }

    private void addDonation() {
        clearErrorLabels();
        String donorName = donorNameField.getText();
        String email = emailField.getText();
        String description = descriptionField.getText();
        String referenceNumber = referenceNumberField.getText();
        String date = dateField.getText();

        if (!validateInputs(donorName, email, description, referenceNumber, date)) {
            return;
        }

        controller.addDonation(new Donation(0, donorName, email, description, referenceNumber, date));
        displayDonations();
        clearInputFields();
    }

    private void updateDonation() {
        if (selectedDonationId != -1) {
            clearErrorLabels();
            String donorName = donorNameField.getText();
            String email = emailField.getText();
            String description = descriptionField.getText();
            String referenceNumber = referenceNumberField.getText();
            String date = dateField.getText();

            if (!validateInputs(donorName, email, description, referenceNumber, date)) {
                return;
            }

            controller.updateDonation(new Donation(selectedDonationId, donorName, email, description, referenceNumber, date));
            displayDonations();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a donation to update", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteDonation() {
        if (selectedDonationId != -1) {
            controller.deleteDonation(selectedDonationId);
            displayDonations();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a donation to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void selectDonation() {
        int selectedRow = donationTable.getSelectedRow();
        if (selectedRow != -1) {
            selectedDonationId = Integer.parseInt(donationTable.getValueAt(selectedRow, 0).toString());
            donorNameField.setText(donationTable.getValueAt(selectedRow, 1).toString());
            emailField.setText(donationTable.getValueAt(selectedRow, 2).toString());
            descriptionField.setText(donationTable.getValueAt(selectedRow, 3).toString());
            referenceNumberField.setText(donationTable.getValueAt(selectedRow, 4).toString());
            dateField.setText(donationTable.getValueAt(selectedRow, 5).toString());
        } else {
            clearInputFields();
        }
    }

    private void displayDonations() {
        List<Donation> donations = controller.getAllDonations();
        String[] columnNames = {"D_ID", "Donor Name", "Email", "Description", "Reference Number", "Date"};
        Object[][] data = new Object[donations.size()][6];

        for (int i = 0; i < donations.size(); i++) {
            data[i][0] = donations.get(i).getDonationId();
            data[i][1] = donations.get(i).getDonorName();
            data[i][2] = donations.get(i).getEmail();
            data[i][3] = donations.get(i).getDescription();
            data[i][4] = donations.get(i).getReferenceNumber();
            data[i][5] = donations.get(i).getDonationDate();
        }
        donationTable.setModel(new DefaultTableModel(data, columnNames));
    }

    private boolean validateInputs(String donorName, String email, String description, String referenceNumber, String date) {
        boolean isValid = true;

        if (!DonationValidation.isNotEmpty(donorName)) {
            donorNameErrorLabel.setText("Donor name is required.");
            isValid = false;
        }

        if (!DonationValidation.isValidEmail(email)) {
            emailErrorLabel.setText("Please enter a valid email.");
            isValid = false;
        }

        if (!DonationValidation.isNotEmpty(description)) {
            descriptionErrorLabel.setText("Description is required.");
            isValid = false;
        }

        if (!DonationValidation.isValidReferenceNumber(referenceNumber)) {
            referenceNumberErrorLabel.setText("Reference number is required.");
            isValid = false;
        }

        if (!DonationValidation.isValidDate(date)) {
            dateErrorLabel.setText("Please enter a valid date (DD/MM/YYYY).");
            isValid = false;
        }

        return isValid;
    }

    private void clearErrorLabels() {
        donorNameErrorLabel.setText("");
        emailErrorLabel.setText("");
        descriptionErrorLabel.setText("");
        referenceNumberErrorLabel.setText("");
        dateErrorLabel.setText("");
    }

    private void clearInputFields() {
        donorNameField.setText("");
        emailField.setText("");
        descriptionField.setText("");
        referenceNumberField.setText("");
        dateField.setText("");
        selectedDonationId = -1;
    }

    private void styleButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 128, 185));
        button.setUI(new RoundedButtonUI(new Color(0x154360), new Color(0x1B4F72))); // Rounded button UI
    }

    private void navigateBack() {
        //Close this frame
        dispose();
        //here you can create and show the volunteer dashboard frame
        VolunteerDashboardFrame volunteerDashboard = new VolunteerDashboardFrame();
        volunteerDashboard.setVisible(true);

    }
}
