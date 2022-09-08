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
    public final boolean isValid(Object o) {
        if (needNotNull && isNull(o)) {
            return false;
        }
        if (needNotNull && !isNumber(o)) {
            return false;
        }
        if (needPositive && !isPositive(o)) {
            return false;
        }
        if (needRange && !inRange(o)) {
            return false;
        }
        return true;
    }

    private boolean isNumber(Object o) {
        if (!isNull(o)) {
            return o instanceof Number;
        }
        return false;
    }

    private boolean isPositive(Object o) {
        if (isNull(o) && !needNotNull) {
            return true;
        }
        if (!isNull(o) && isNumber(o)) {
            int x = (int) o;
            return x > 0;
        }
        return false;
    }

    private boolean inRange(Object o) {
        if (!isNull(o) && isNumber(o)) {
            int x = (int) o;
            return x >= lowerBound && x <= upperBound;
        }
        return false;
    }
}
