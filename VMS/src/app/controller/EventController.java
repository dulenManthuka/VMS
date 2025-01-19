package app.controller;

import app.model.Event;
import app.dao.EventDAO;

import java.sql.SQLException;
import java.util.List;

public class EventController {
    private final EventDAO eventDAO;

    public EventController() {
        eventDAO = new EventDAO();
    }

    public void addEvent(Event event) {
        try {
            eventDAO.addEvent(event);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Event> getAllEvents() {
        try {
            return eventDAO.getAllEvents();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateEvent(Event event) {
        try {
            eventDAO.updateEvent(event);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an event by ID
    public void deleteEvent(int eventId) {
        try {
            eventDAO.deleteEvent(eventId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // In your EventController
    public boolean isEventNameUnique(String eventName) throws SQLException {
        return eventDAO.isEventNameUnique(eventName);
    }


}
