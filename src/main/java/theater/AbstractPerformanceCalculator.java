package theater;

/**
 * Base calculator for determining the amount and volume credits
 * for a single performance.
 */
public abstract class AbstractPerformanceCalculator {

    private final Performance performance;
    private final Play play;

    /**
     * Construct a performance calculator.
     *
     * @param performance performance being priced
     * @param play        play being performed
     */
    protected AbstractPerformanceCalculator(Performance performance,
                                            Play play) {
        this.performance = performance;
        this.play = play;
    }

    /**
     * Get the performance associated with this calculator.
     *
     * @return the performance
     */
    protected Performance getPerformance() {
        return performance;
    }

    /**
     * Get the play associated with this calculator.
     *
     * @return the play
     */
    protected Play getPlay() {
        return play;
    }

    /**
     * Calculate the amount owed for this performance in cents.
     *
     * @return amount in cents
     */
    public abstract int amountFor();

    /**
     * Calculate the base volume credits earned for this performance.
     *
     * @return volume credits
     */
    public int volumeCredits() {
        return Math.max(
                performance.getAudience()
                        - Constants.BASE_VOLUME_CREDIT_THRESHOLD, 0);
    }

    /**
     * Factory method to construct the appropriate calculator subtype
     * for the given performance and play.
     *
     * @param performance performance being priced
     * @param play        play being performed
     * @return calculator instance
     */
    public static AbstractPerformanceCalculator createPerformanceCalculator(
            Performance performance, Play play) {
        switch (play.getType()) {
            case "tragedy":
                return new TragedyCalculator(performance, play);
            case "comedy":
                return new ComedyCalculator(performance, play);
            default:
                throw new RuntimeException(
                        String.format("unknown type: %s", play.getType()));
        }
    }
}
