package app.model;

public class Donation {
    private int donationId;
    private String donorName;
    private String email;
    private String description;
    private String referenceNumber;
    private String donationDate;

    // Constructor
    public Donation(int donationId, String donorName, String email, String description, String referenceNumber, String donationDate) {
        this.donationId = donationId;
        this.donorName = donorName;
        this.email = email;
        this.description = description;
        this.referenceNumber = referenceNumber;
        this.donationDate = donationDate;
    }

    // Getters and Setters
    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
}
