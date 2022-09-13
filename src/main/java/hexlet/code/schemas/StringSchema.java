package hexlet.code.schemas;

public class StringSchema extends BaseSchema {
    public final StringSchema required() {
        addCheck(x -> x instanceof String && !((String) x).isEmpty());
        return this;
    }

    public final StringSchema minLength(int min) {
        addCheck(x -> x instanceof String && ((String) x).length() >= min);
        return this;
    }

    public final StringSchema contains(String s) {
        addCheck(x -> x instanceof String && ((String) x).contains(s));
        return this;
    }
}
