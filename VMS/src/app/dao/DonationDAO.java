package app.dao;

import app.config.dbConnection;
import app.model.Donation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationDAO {
    // Add Donation
    public void addDonation(Donation donation) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO donation (donor_name, email, description, reference_number, donation_date) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, donation.getDonorName());
        ps.setString(2, donation.getEmail());
        ps.setString(3, donation.getDescription());
        ps.setString(4, donation.getReferenceNumber());
        ps.setString(5, donation.getDonationDate());
        ps.executeUpdate();
        connection.close();
    }

    // Retrieve all donation entries
    public List<Donation> getAllDonations() throws SQLException {
        List<Donation> donations = new ArrayList<>();
        Connection connection = dbConnection.getConnection();
        String query = "SELECT * FROM donation";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Donation donation = new Donation(
                    rs.getInt("D_ID"),
                    rs.getString("donor_name"),
                    rs.getString("email"),
                    rs.getString("description"),
                    rs.getString("reference_number"),
                    rs.getString("donation_date")
            );
            donations.add(donation);
        }
        connection.close();
        return donations;
    }

    // Update Donation
    public void updateDonation(Donation donation) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String query = "UPDATE donation SET donor_name = ?, email = ?, description = ?, reference_number = ?, donation_date = ? WHERE D_ID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, donation.getDonorName());
        ps.setString(2, donation.getEmail());
        ps.setString(3, donation.getDescription());
        ps.setString(4, donation.getReferenceNumber());
        ps.setString(5, donation.getDonationDate());
        ps.setInt(6, donation.getDonationId());
        ps.executeUpdate();
        connection.close();
    }

    // Delete Donation by ID
    public void deleteDonation(int donationId) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String query = "DELETE FROM donation WHERE D_ID = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, donationId);
        ps.executeUpdate();
        connection.close();
    }
}
