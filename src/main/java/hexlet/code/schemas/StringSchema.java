package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema {
    private boolean needNotEmpty;
    private int minStringLength;
    private List<String> requiredContent;
    private String string;

    public final StringSchema required() {
        this.needNotEmpty = true;
        return this;
    }

    public final StringSchema minLength(int min) {
        this.minStringLength = min;
        return this;
    }

    public final StringSchema contains(String str) {
        if (this.requiredContent == null) {
            this.requiredContent = new ArrayList<>();
        }
        this.requiredContent.add(str);
        return this;
    }

    @Override
    public final boolean isValid(Object o) {
        setObjectToValidate(o);
        if (isString()) {
            this.string = (String) getObjectToValidate();
        } else {
            return false;
        }
        return checkConditions(
                needNotEmpty && hasEmpty(),
                minStringLength != 0 && !hasValidLength(),
                requiredContent != null && !hasRequiredContent()
        );
    }

    private boolean isString() {
        if (needNotEmpty && !isNull()) {
            return getObjectToValidate() instanceof String;
        }
        return true;
    }

    private boolean hasEmpty() {
        return this.string == null || this.string.isEmpty();
    }

    private boolean hasValidLength() {
        if (!hasEmpty() && this.string.length() < this.minStringLength) {
            return false;
        }
        return true;
    }

    private boolean hasRequiredContent() {
        if (!hasEmpty()) {
            for (String s : requiredContent) {
                if (!this.string.contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }
}
