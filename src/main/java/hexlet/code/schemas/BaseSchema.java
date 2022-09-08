package hexlet.code.schemas;

public abstract class BaseSchema {
    protected Object o;

    public abstract boolean isValid(Object o);
    public final boolean isNull() {
        return o == null;
    }
}
