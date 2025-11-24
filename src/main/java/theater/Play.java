package theater;

/**
 * Class representing a play.
 */
public class Play {

    private final String name;
    private final String type;

    /**
     * Construct a new play.
     *
     * @param name play name
     * @param type play type (e.g., tragedy, comedy)
     */
    public Play(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
