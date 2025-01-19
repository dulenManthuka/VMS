package app.controller;

import app.model.Donation;
import app.dao.DonationDAO;

import java.sql.SQLException;
import java.util.List;

public class DonationController {
    private final DonationDAO donationDAO;

    public DonationController() {
        donationDAO = new DonationDAO();
    }

    // Add a donation
   public void addDonation(Donation donation) {
        try {
            donationDAO.addDonation(donation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all donations
    public List<Donation> getAllDonations() {
        try {
            return donationDAO.getAllDonations();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update a donation
    public void updateDonation(Donation donation) {
        try {
            donationDAO.updateDonation(donation);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a donation by ID
    public void deleteDonation(int donationId) {
        try {
            donationDAO.deleteDonation(donationId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

