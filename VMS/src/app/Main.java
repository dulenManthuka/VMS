package app;

import app.model.Donation;
import app.view.*;
import app.view.EventManagementFrame;
import app.view.FeedbackFrame;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
           AdminDashboardFrame frame = new AdminDashboardFrame();
           //xDonationFrame frame = new DonationFrame();
            //LandingFrame frame = new LandingFrame();
           frame.setVisible(true);
        });
    }
}
