package app.view;

import app.view.components.RoundedButtonUI;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {

    public AdminDashboardFrame() {
        setTitle("Admin Dashboard");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Layout (Split into Left and Right)
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left Navigation Panel
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BoxLayout(navPanel, BoxLayout.Y_AXIS));
        navPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Add padding
        navPanel.setBackground(new Color(0x1B4F72)); // Dark blue background for navigation panel

        // Dashboard Header on the left
        JLabel headerLabel = new JLabel("Admin Dashboard");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE); // White text for header
        headerLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        navPanel.add(headerLabel);
        navPanel.add(Box.createRigidArea(new Dimension(0, 40))); // Space between header and buttons

        // View Events Button
        JButton viewEventsButton = new JButton("Manage Volunteers");
        styleButton(viewEventsButton);
        viewEventsButton.addActionListener(e -> viewEvents());
        navPanel.add(viewEventsButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing between buttons

        // Manage Events Button
        JButton manageEventsButton = new JButton("Manage Events");
        styleButton(manageEventsButton);
        manageEventsButton.addActionListener(e -> manageEvents());
        navPanel.add(manageEventsButton);
        navPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Add spacing between buttons

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        styleButton(logoutButton);
        logoutButton.addActionListener(e -> logout());
        navPanel.add(logoutButton);

        // Right Panel for Image
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBackground(Color.WHITE); // White background for right panel

        // Load and add the image
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\HP\\OneDrive\\Desktop\\VMS (1)\\VMS\\src\\resources\\images\\adminDashboard.jpg"); // Replace with actual image path
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(800, 700, Image.SCALE_SMOOTH); // Scale image to fit the panel size
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        rightPanel.add(imageLabel, BorderLayout.CENTER); // Add image label to fill the right panel

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
        button.setBackground(new Color(0x154360)); // Slightly lighter blue for buttons
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        button.setUI(new RoundedButtonUI(new Color(0x154360), new Color(0x1B4F72))); // Rounded button UI
    }

    // Method to handle viewing events
    private void viewEvents() {
        new VolunteerManagementFrame().setVisible(true); // Open the Event Management Frame
        this.dispose();        // You can replace this with navigation to the Manage Volunteers screen
    }

    // Method to handle managing events
    private void manageEvents() {
        new EventManagementFrame().setVisible(true); // Open the Event Management Frame
        this.dispose();  // Close the Admin Dashboard
    }

    // Method to handle logging out
    private void logout() {
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            new LandingFrame().setVisible(true);  // Navigate back to the landing page
            this.dispose();  // Close the Admin Dashboard
        }
    }
}