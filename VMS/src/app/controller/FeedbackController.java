package app.controller;

import app.model.Feedback;
import app.dao.FeedbackDAO;

import java.sql.SQLException;
import java.util.List;

public class FeedbackController {
    private final FeedbackDAO feedbackDAO;

    public FeedbackController() {
        feedbackDAO = new FeedbackDAO();
    }

    // Add feedback
    public void addFeedback(Feedback feedback) {
        try {
            feedbackDAO.addFeedback(feedback);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all feedback entries
    public List<Feedback> getAllFeedbacks() {
        try {
            return feedbackDAO.getAllFeedbacks();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update a feedback entry
    public void updateFeedback(Feedback feedback) {
        try {
            feedbackDAO.updateFeedback(feedback);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a feedback entry by ID
    public void deleteFeedback(int feedbackId) {
        try {
            feedbackDAO.deleteFeedback(feedbackId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
