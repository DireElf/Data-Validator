package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private double lowerBound;
    private double upperBound;

    public final NumberSchema required() {
        addCheck("isRequired");
        return this;
    }

    public final NumberSchema positive() {
        addCheck("isPositive");
        return this;
    }

    public final NumberSchema range(double min, double max) {
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
            return ((Number) o).doubleValue() > 0;
        }
    }

    private boolean inRange(Object o) {
        if (!(o instanceof Number)) {
            return false;
        } else {
            double x = ((Number) o).doubleValue();
            return x >= lowerBound && x <= upperBound;
        }
    }
}
