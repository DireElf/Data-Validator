package hexlet.code.schemas;

public abstract class BaseSchema {
    private Object objectToValidate;

    public abstract boolean isValid(Object o);

    public final boolean checkConditions(boolean... args) {
        for (boolean b : args) {
            if (b) {
                return false;
            }
        }
        return true;
    }

    public final boolean isNull() {
        return objectToValidate == null;
    }

    //getter & setter
    public final Object getObjectToValidate() {
        return objectToValidate;
    }

    public final void setObjectToValidate(Object o) {
        this.objectToValidate = o;
    }
}
