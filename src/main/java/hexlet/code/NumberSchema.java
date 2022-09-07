package hexlet.code;

public class NumberSchema extends BaseSchema {
    private boolean needNotNull;
    private boolean isPositive;
    private int lowerBound;
    private int upperBound;

    public final NumberSchema required() {
        this.needNotNull = true;
        return this;
    }

    public final NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public final NumberSchema range(int min, int max) {
        this.lowerBound = min;
        this.upperBound = max;
        return this;
    }

    @Override
    public final void check(Object o) {
        boolean isNull = o == null;
        if (needNotNull && isNull) {
            getValidationResults().add(false);
            return;
        }
        boolean isNumber = o instanceof Number;
        if (!isNull && !isNumber) {
            getValidationResults().add(false);
            return;
        }
        if (isPositive) {
            if (!isNull) {
                int x = (int) o;
                if (x <= 0) {
                    getValidationResults().add(false);
                    return;
                }
            }
        }
        if (!isNull) {
            boolean hasRange = lowerBound != 0 && upperBound != 0;
            if (hasRange) {
                int x = (int) o;
                if (x < lowerBound || x > upperBound) {
                    getValidationResults().add(false);
                }
            }
        }
    }
}
