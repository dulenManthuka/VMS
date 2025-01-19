package app.controller;

import app.model.Volunteer;
import app.dao.VolunteerDAO;

import java.sql.SQLException;
import java.util.List;

public class VolunteerController {
    private final VolunteerDAO volunteerDAO;

    public VolunteerController() {
        volunteerDAO = new VolunteerDAO();
    }

    // Add a new volunteer
    public void addVolunteer(Volunteer volunteer) {
        try {
            volunteerDAO.addVolunteer(volunteer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all volunteers
    public List<Volunteer> getAllVolunteers() {
        try {
            return volunteerDAO.getAllVolunteers();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update a volunteer
    public void updateVolunteer(Volunteer volunteer) {
        try {
            volunteerDAO.updateVolunteer(volunteer);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a volunteer by ID
    public void deleteVolunteer(int volunteerId) {
        try {
            volunteerDAO.deleteVolunteer(volunteerId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
