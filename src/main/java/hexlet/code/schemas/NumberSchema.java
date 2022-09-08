package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean hasRequired;
    private boolean shouldPositive;
    private boolean hasRange;
    private int lowerBound;
    private int upperBound;

    public final NumberSchema required() {
        this.hasRequired = true;
        return this;
    }

    public final NumberSchema positive() {
        this.shouldPositive = true;
        return this;
    }

    public final NumberSchema range(int min, int max) {
        this.hasRange = true;
        this.lowerBound = min;
        this.upperBound = max;
        return this;
    }

    @Override
    public final boolean isValid(Object o) {
        return checkConditions(isRequired(o), isPositive(o), inRange(o));
    }

    private boolean isRequired(Object o) {
        if (!hasRequired) {
            return true;
        }
        return o instanceof Number;
    }

    private boolean isPositive(Object o) {
        if (!shouldPositive || o == null) {
            return true;
        }
        if (!(o instanceof Number)) {
            return false;
        } else {
            return (int) o > 0;
        }
    }

    private boolean inRange(Object o) {
        if (!hasRange) {
            return true;
        }
        if (!(o instanceof Number)) {
            return false;
        } else {
            int x = (int) o;
            return x >= lowerBound && x <= upperBound;
        }
    }
}
