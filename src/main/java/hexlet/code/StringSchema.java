package hexlet.code;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema {
    private boolean needNotEmpty;
    private int minStringLength;
    private List<String> requiredContent;

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
        String str = (String) o;
        if (needNotEmpty && hasEmpty(str)) {
            return false;
        }
        if (minStringLength != 0 && !hasValidLength(str)) {
            return false;
        }
        if (requiredContent != null && !hasRequiredContent(str)) {
            return false;
        }
        return true;
    }

    private boolean hasEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean hasValidLength(String str) {
        if (!hasEmpty(str) && str.length() < this.minStringLength) {
            return false;
        }
        return true;
    }

    private boolean hasRequiredContent(String str) {
        if (!hasEmpty(str)) {
            for (String s : requiredContent) {
                if (!str.contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }
}
