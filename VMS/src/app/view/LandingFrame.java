package app.view;

import app.view.components.RoundedButtonUI;

import javax.swing.*;
import java.awt.*;

public class LandingFrame extends JFrame {

    ImageIcon originalIcon = new ImageIcon("C:\\Users\\HP\\OneDrive\\Desktop\\VMS (1)\\VMS\\src\\resources\\images\\landingPage.png");
    Image originalImage = originalIcon.getImage();
    Image resizedImage = originalImage.getScaledInstance(250, 280, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(resizedImage);
    public LandingFrame() {
        setTitle("Volunteer Management System");
        setSize(600, 400); // Larger window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));

        JLabel titleLabel = new JLabel("Volunteer Management", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel, BorderLayout.PAGE_START);

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));

        JLabel logoLabel = new JLabel(resizedIcon);
        contentPanel.add(logoLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton adminButton = new JButton("Login as Admin");
        styleButton(adminButton);
        adminButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align buttons
        adminButton.addActionListener(e -> openLoginPage("Admin"));
        buttonPanel.add(adminButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add space between buttons

        // Button to login as Volunteer
        JButton volunteerButton = new JButton("Login as Volunteer");
        styleButton(volunteerButton);
        volunteerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        volunteerButton.addActionListener(e -> openLoginPage("Volunteer"));
        buttonPanel.add(volunteerButton);

        contentPanel.add(buttonPanel, BorderLayout.CENTER); // Add buttons next to the logo
        mainPanel.add(contentPanel, BorderLayout.LINE_END); // Add content panel to the main panel

        getContentPane().add(mainPanel);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 18)); // Larger font size
        button.setMaximumSize(new Dimension(200, 50)); // Set size for buttons
        button.setForeground(Color.WHITE); // White text
        button.setFocusPainted(false); // Remove focus border
        button.setContentAreaFilled(false); // Don't fill the area by default
        button.setOpaque(false); // Make it non-opaque
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // Set custom button UI for rounded corners and color change
        button.setUI(new RoundedButtonUI(new Color(0x1B4F72), new Color(0x154360))); // Set dark blue and pressed colors
    }



    private void openLoginPage(String role) {
        if ("Admin".equals(role)) {
            new LoginFrame("Admin").setVisible(true); // Open Admin Login Page
        } else {
            new LoginFrame("Volunteer").setVisible(true); // Open Volunteer Login Page
        }
        this.dispose(); // Close the home frame
    }
}
