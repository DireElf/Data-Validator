package hexlet.code.schemas;

public class NumberSchema extends BaseSchema {
    public final NumberSchema required() {
        addCheck(x -> x instanceof Number);
        return this;
    }

    public final NumberSchema positive() {
        addCheck(x -> x == null || x instanceof Number && ((Number) x).doubleValue() > 0);
        return this;
    }

    public final NumberSchema range(double min, double max) {
        addCheck(x -> x instanceof Number
                && ((Number) x).doubleValue() >= min
                && ((Number) x).doubleValue() <= max);
        return this;
    }
}
