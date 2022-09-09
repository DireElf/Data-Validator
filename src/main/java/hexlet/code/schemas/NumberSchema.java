package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private int lowerBound;
    private int upperBound;

    public final NumberSchema required() {
        addCheck("isRequired");
        return this;
    }

    public final NumberSchema positive() {
        addCheck("isPositive");
        return this;
    }

    public final NumberSchema range(int min, int max) {
        addCheck("inRange");
        this.lowerBound = min;
        this.upperBound = max;
        return this;
    }

    private boolean isRequired(Object o) {
        return o instanceof Number;
    }

    private boolean isPositive(Object o) {
        if (o == null) {
            return true;
        }
        if (!(o instanceof Number)) {
            return false;
        } else {
            return (int) o > 0;
        }
    }

    private boolean inRange(Object o) {
        if (!(o instanceof Number)) {
            return false;
        } else {
            int x = (int) o;
            return x >= lowerBound && x <= upperBound;
        }
    }
}
