package app.view;

import app.view.components.RoundedButtonUI;

import javax.swing.*;
import java.awt.*;

public class VolunteerDashboardFrame extends JFrame {

    public VolunteerDashboardFrame() {
        setTitle("Volunteer Dashboard");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout (Split into Left and Right)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Add padding
        navPanel.setBackground(new Color(0x2874A6)); // Different shade of blue for volunteer panel

        // Dashboard Header on the left
        JLabel headerLabel = new JLabel("Volunteer Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE); // White text for header
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        navPanel.add(headerLabel);
        navPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Space between header and buttons

      // Donation Button
        JButton donationButton = new JButton("Donation");
        styleButton(donationButton);
        donationButton.addActionListener(e -> makeDonation());
        navPanel.add(donationButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing between buttons

        // Feedback Button
        JButton feedbackButton = new JButton("Feedback");
        styleButton(feedbackButton);
        feedbackButton.addActionListener(e -> provideFeedback());
        navPanel.add(feedbackButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing between buttons

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.addActionListener(e -> logout());
        navPanel.add(logoutButton);

        // Right Panel for Image
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.WHITE); // White background for right panel
        JLabel imageLabel = new JLabel(new ImageIcon("C:\\Users\\HP\\OneDrive\\Desktop\\VMS (1)\\VMS\\src\\resources\\images\\volunteerDashboard.jpg")); // Replace with actual image path
        rightPanel.add(imageLabel); // Center the image in the right panel

        // Add panels to the main panel
        mainPanel.add(navPanel, BorderLayout.WEST); // Add navigation panel to the left
        mainPanel.add(rightPanel, BorderLayout.CENTER); // Add image panel to the center

        // Add the main panel to the frame
        getContentPane().add(mainPanel);
    }

    // Helper method to style buttons consistently
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setMaximumSize(new Dimension(250, 50)); // Set consistent button size
        button.setBackground(new Color(0x1A5276)); // Slightly lighter blue for buttons
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setUI(new RoundedButtonUI(new Color(0x1A5276), new Color(0x2874A6))); // Rounded button UI
    }

    // Method to handle making a donation
    private void makeDonation() {
        new DonationFrame().setVisible(true); // Open the Donation Frame
        this.dispose(); // Close the Volunteer Dashboard
    }

    // Method to handle providing feedback
    private void provideFeedback() {
        new FeedbackFrame().setVisible(true); // Open the Feedback Frame
        this.dispose(); // Close the Volunteer Dashboard
    }

    // Method to handle logging out
    private void logout() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            new LandingFrame().setVisible(true);  // Navigate back to the landing page
            this.dispose();  // Close the Volunteer Dashboard
        }
    }
}
