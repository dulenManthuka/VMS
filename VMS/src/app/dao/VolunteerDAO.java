package app.dao;

import app.config.dbConnection;
import app.model.Volunteer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VolunteerDAO {
    // Add Volunteer
    public void addVolunteer(Volunteer volunteer) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String query = "INSERT INTO volunteers (firstName, lastName, email, userName, password, address, currentStatus, skills) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, volunteer.getFirstName());
        ps.setString(2, volunteer.getLastName());
        ps.setString(3, volunteer.getEmail());
        ps.setString(4, volunteer.getUserName());
        ps.setString(5, volunteer.getPassword());
        ps.setString(6, volunteer.getAddress());
        ps.setString(7, volunteer.getCurrentStatus());
        ps.setString(8, volunteer.getSkills());
        ps.executeUpdate();
        connection.close();
    }

    // Get All Volunteers
    public List<Volunteer> getAllVolunteers() throws SQLException {
        List<Volunteer> volunteers = new ArrayList<>();
        Connection connection = dbConnection.getConnection();
        String query = "SELECT * FROM volunteers";
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            Volunteer volunteer = new Volunteer(rs.getInt("volunteer_id"), rs.getString("firstName"),
                    rs.getString("lastName"), rs.getString("email"),
                    rs.getString("userName"), rs.getString("password"),
                    rs.getString("address"), rs.getString("currentStatus"),
                    rs.getString("skills"));
            volunteers.add(volunteer);
        }
        connection.close();
        return volunteers;
    }

    // Update Volunteer
    public void updateVolunteer(Volunteer volunteer) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String query = "UPDATE volunteers SET firstName = ?, lastName = ?, email = ?, userName = ?, password = ?, address = ?, currentStatus = ?, skills = ? WHERE volunteer_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, volunteer.getFirstName());
        ps.setString(2, volunteer.getLastName());
        ps.setString(3, volunteer.getEmail());
        ps.setString(4, volunteer.getUserName());
        ps.setString(5, volunteer.getPassword());
        ps.setString(6, volunteer.getAddress());
        ps.setString(7, volunteer.getCurrentStatus());
        ps.setString(8, volunteer.getSkills());
        ps.setInt(9, volunteer.getVolunteerId());
        ps.executeUpdate();
        connection.close();
    }

    // Delete Volunteer
    public void deleteVolunteer(int volunteerId) throws SQLException {
        Connection connection = dbConnection.getConnection();
        String query = "DELETE FROM volunteers WHERE volunteer_id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, volunteerId);
        ps.executeUpdate();
        connection.close();
    }
}
