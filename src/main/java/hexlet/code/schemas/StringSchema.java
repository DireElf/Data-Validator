package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema {
    private boolean needNotEmpty;
    private int minStringLength;
    private List<String> requiredContent;
    private String str;

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
    public final boolean isValid(Object object) {
        this.o = object;
        if (isString()) {
            this.str = (String) o;
        } else {
            return false;
        }
        if (needNotEmpty && hasEmpty()) {
            return false;
        }
        if (minStringLength != 0 && !hasValidLength()) {
            return false;
        }
        if (requiredContent != null && !hasRequiredContent()) {
            return false;
        }
        return true;
    }

    private boolean isString() {
        if (needNotEmpty && !isNull()) {
            return this.o instanceof String;
        }
        return true;
    }

    private boolean hasEmpty() {
        return this.str == null || this.str.isEmpty();
    }

    private boolean hasValidLength() {
        if (!hasEmpty() && this.str.length() < this.minStringLength) {
            return false;
        }
        return true;
    }

    private boolean hasRequiredContent() {
        if (!hasEmpty()) {
            for (String s : requiredContent) {
                if (!this.str.contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }
}
