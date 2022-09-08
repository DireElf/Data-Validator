package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema {
    private boolean hasRequired;
    private boolean hasMinLength;
    private boolean hasRequiredContent;

    private int minStringLength;
    private List<String> requiredContent;

    public final StringSchema required() {
        this.hasRequired = true;
        return this;
    }

    public final StringSchema minLength(int min) {
        this.hasMinLength = true;
        this.minStringLength = min;
        return this;
    }

    public final StringSchema contains(String s) {
        this.hasRequiredContent = true;
        if (this.requiredContent == null) {
            this.requiredContent = new ArrayList<>();
        }
        this.requiredContent.add(s);
        return this;
    }

    @Override
    public final boolean isValid(Object o) {
        return checkConditions(isRequired(o), isLengthValid(o), hasStrings(o));
    }

    private boolean isRequired(Object o) {
        if (!hasRequired) {
            return true;
        }
        return o instanceof String && !((String) o).isEmpty();
    }

    private boolean isLengthValid(Object o) {
        if (!hasMinLength) {
            return true;
        } else {
            return o instanceof String && ((String) o).length() >= this.minStringLength;
        }
    }

    private boolean hasStrings(Object o) {
        if (!hasRequiredContent) {
            return true;
        }
        if (!(o instanceof String)) {
            return false;
        }
        String string = (String) o;
        for (String s : requiredContent) {
            if (!string.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
