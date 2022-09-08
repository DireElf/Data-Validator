package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    private boolean needNotNull;
    private boolean needPositive;
    private boolean needRange;
    private int lowerBound;
    private int upperBound;

    public final NumberSchema required() {
        this.needNotNull = true;
        return this;
    }

    public final NumberSchema positive() {
        this.needPositive = true;
        return this;
    }

    public final NumberSchema range(int min, int max) {
        this.needRange = true;
        this.lowerBound = min;
        this.upperBound = max;
        return this;
    }

    @Override
    public final boolean isValid(Object object) {
        this.o = object;
        if (needNotNull && isNull()) {
            return false;
        }
        if (needNotNull && !isNumber()) {
            return false;
        }
        if (needPositive && !isPositive()) {
            return false;
        }
        if (needRange && !inRange()) {
            return false;
        }
        return true;
    }

    private boolean isNumber() {
        if (!isNull()) {
            return o instanceof Number;
        }
        return false;
    }

    private boolean isPositive() {
        if (isNull() && !needNotNull) {
            return true;
        }
        if (!isNull() && isNumber()) {
            int x = (int) o;
            return x > 0;
        }
        return false;
    }

    private boolean inRange() {
        if (!isNull() && isNumber()) {
            int x = (int) o;
            return x >= lowerBound && x <= upperBound;
        }
        return false;
    }
}
