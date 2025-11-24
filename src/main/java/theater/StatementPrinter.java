package theater;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

/**
 * Generates plain-text statements for a given invoice of performances.
 */
public class StatementPrinter {

    private final StatementData statementData;

    /**
     * Create a new statement printer for the given invoice and map of plays.
     *
     * @param invoice invoice to print
     * @param plays   available plays keyed by play id
     */
    public StatementPrinter(Invoice invoice, Map<String, Play> plays) {
        this.statementData = new StatementData(invoice, plays);
    }

    /**
     * Returns a formatted plain-text statement of the invoice associated with
     * this printer.
     *
     * @return the formatted statement
     */
    public String statement() {
        return renderPlainText();
    }

    /**
     * Format the statement in plain text.
     *
     * @return plain-text statement
     */
    protected String renderPlainText() {
        StringBuilder result = new StringBuilder(
                String.format("Statement for %s%n",
                        statementData.getCustomer()));
        for (PerformanceData performanceData : statementData.getPerformances()) {
            result.append(String.format("  %s: %s (%s seats)%n",
                    performanceData.getName(),
                    usd(performanceData.getAmount()),
                    performanceData.getAudience()));
        }
        result.append(String.format("Amount owed is %s%n",
                usd(statementData.totalAmount())));
        result.append(String.format("You earned %s credits%n",
                statementData.volumeCredits()));
        return result.toString();
    }

    /**
     * Helper for formatting an amount as a US currency string.
     *
     * @param amount amount in cents
     * @return formatted currency string
     */
    protected String usd(int amount) {
        NumberFormat formatter =
                NumberFormat.getCurrencyInstance(Locale.US);
        double dollars = (double) amount / Constants.PERCENT_FACTOR;
        return formatter.format(dollars);
    }

    /**
     * Accessor for subclasses that need the underlying statement data.
     *
     * @return statement data
     */
    protected StatementData getStatementData() {
        return statementData;
    }
}
