package theater;

/**
 * Class representing a performance of a play.
 */
public class Performance {

    private final String playID;
    private final int audience;

    /**
     * Construct a new performance entry.
     *
     * @param playID   play identifier
     * @param audience audience size
     */
    public Performance(String playID, int audience) {
        this.playID = playID;
        this.audience = audience;
    }

    public String getPlayID() {
        return playID;
    }

    public int getAudience() {
        return audience;
    }
}
