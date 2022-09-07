package hexlet.code;

public abstract class BaseSchema {
    public abstract boolean isValid(Object o);

    public final boolean isNull(Object o) {
        return o == null;
    }
}
