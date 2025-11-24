package theater;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Data-transfer object containing all information needed
 * to render a statement for an invoice.
 */
public class StatementData {

    private final String customer;
    private final List<PerformanceData> performances;

    /**
     * Build statement data from an invoice and the available plays.
     *
     * @param invoice invoice to summarize
     * @param plays   plays keyed by id
     */
    public StatementData(Invoice invoice, Map<String, Play> plays) {
        this.customer = invoice.getCustomer();
        this.performances = new ArrayList<>();
        for (Performance performance : invoice.getPerformances()) {
            performances.add(createPerformanceData(performance, plays));
        }
    }

    private PerformanceData createPerformanceData(Performance performance,
                                                  Map<String, Play> plays) {
        Play play = plays.get(performance.getPlayID());
        AbstractPerformanceCalculator calculator =
                AbstractPerformanceCalculator.createPerformanceCalculator(
                        performance, play);
        int amount = calculator.amountFor();
        int volumeCredits = calculator.volumeCredits();
        return new PerformanceData(play.getName(), performance.getAudience(),
                amount, volumeCredits);
    }

    /**
     * Customer name for this statement.
     *
     * @return customer name
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * All performance data entries for this statement.
     *
     * @return list of performances
     */
    public List<PerformanceData> getPerformances() {
        return new ArrayList<>(performances);
    }

    /**
     * Calculate total amount owed for all performances.
     *
     * @return total amount in cents
     */
    public int totalAmount() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getAmount();
        }
        return result;
    }

    /**
     * Calculate total volume credits for all performances.
     *
     * @return total volume credits
     */
    public int volumeCredits() {
        int result = 0;
        for (PerformanceData performanceData : performances) {
            result += performanceData.getVolumeCredits();
        }
        return result;
    }
}
