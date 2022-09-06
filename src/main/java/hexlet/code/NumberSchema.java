package hexlet.code;

public class NumberSchema {
    private boolean notNull;
    private boolean isPositive;
    private int lowerBound;
    private int upperBound;

    public final void required() {
        notNull = true;
    }

    public final boolean isValid(Object o) {
        boolean isNull = o == null;
        if (notNull && isNull) {
            return false;
        }
        boolean isNumber = o instanceof Number;
        if (!isNull && !isNumber) {
            return false;
        }
        if (!isNull) {
            int x = (int) o;
            if (isPositive && x <= 0) {
                return false;
            }
            boolean hasRange = lowerBound != 0 && upperBound != 0;
            if (hasRange) {
                return x >= lowerBound && x <= upperBound;
            }
        }
        return true;
    }

    public final void positive() {
        isPositive = true;
    }

    public final void range(int min, int max) {
        lowerBound = min;
        upperBound = max;
    }
}
