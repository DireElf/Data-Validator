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
    public final void check(Object o) {
        String str = (String) o;
        boolean isEmpty = str == null || str.length() == 0;
        if (needNotEmpty & isEmpty) {
            getValidationResults().add(false);
            return;
        }
        if (!isEmpty && str.length() < minStringLength) {
            getValidationResults().add(false);
            return;
        }
        if (!isEmpty && requiredContent != null) {
            for (String s : requiredContent) {
                if (!str.contains(s)) {
                    getValidationResults().add(false);
                    return;
                }
            }
        }
    }
}
