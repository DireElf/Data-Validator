package hexlet.code.schemas;

public abstract class BaseSchema {
    public abstract boolean isValid(Object o);

    public final boolean checkConditions(boolean... args) {
        for (boolean b : args) {
            if (!b) {
                return false;
            }
        }
        return true;
    }
}
