package theater;

/**
 * Immutable data for a single performance in a statement.
 */
public class PerformanceData {

    private final String name;
    private final int audience;
    private final int amount;
    private final int volumeCredits;

    /**
     * Construct a performance data object.
     *
     * @param name          play name
     * @param audience      audience size
     * @param amount        amount owed in cents
     * @param volumeCredits volume credits earned
     */
    public PerformanceData(String name, int audience, int amount,
                           int volumeCredits) {
        this.name = name;
        this.audience = audience;
        this.amount = amount;
        this.volumeCredits = volumeCredits;
    }

    public String getName() {
        return name;
    }

    public int getAudience() {
        return audience;
    }

    public int getAmount() {
        return amount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
