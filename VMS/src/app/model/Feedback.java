package app.model;

public class Feedback {

    private int F_ID;
    private String message;
    private String feedbackDate;

    public Feedback(int F_ID, String message, String feedbackDate) {
        this.F_ID = F_ID;
        this.message = message;
        this.feedbackDate = feedbackDate;
    }

    public int getF_ID() {
        return F_ID;
    }

    public void setF_ID(int F_ID) {
        this.F_ID = F_ID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }
}
