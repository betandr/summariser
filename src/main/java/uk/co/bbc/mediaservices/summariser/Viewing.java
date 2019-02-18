package uk.co.bbc.mediaservices.summariser;

/**
 * Represents an idempotent single audience viewing.
 */
public class Viewing {
    private int dateInEpochSeconds;
    private int userIdentifier;
    private String programmeName;
    private int watchTimeInSeconds;
    private String deviceType;

    public Viewing(
        int dateInEpochSeconds,
        int userIdentifier,
        String programmeName,
        int watchTimeInSeconds,
        String deviceType) {
            this.dateInEpochSeconds = dateInEpochSeconds;
            this.userIdentifier = userIdentifier;
            this.programmeName = programmeName;
            this.watchTimeInSeconds = watchTimeInSeconds;
            this.deviceType = deviceType;
        }

    public int getDateInEpochSeconds() {
        return this.dateInEpochSeconds;
    }

    public int getUserIdentifier() {
        return this.userIdentifier;
    }

    public String getProgrammeName() {
        return this.programmeName;
    }

    public int getWatchTimeInSeconds() {
        return this.watchTimeInSeconds;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String toString() {
        return "Date in Epoch Seconds: " + this.dateInEpochSeconds +
            "\nUser Identifier: " + this.userIdentifier +
            "\nProgramme Name: " + this.programmeName +
            "\nWatch Time In Seconds: " + this.watchTimeInSeconds +
            "\nDevice Type: " + this.deviceType + "\n";
    }
}
