package hexlet.code;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSchema {
    private final List<Boolean> validationResults = new ArrayList<>();

    public final List<Boolean> getValidationResults() {
        return validationResults;
    }

    public final boolean isValid(Object o) {
        check(o);
        boolean result = validationResults.size() == 0 ? true : !validationResults.contains(false);
        validationResults.clear();
        return result;
    }

    public abstract void check(Object o);
}
