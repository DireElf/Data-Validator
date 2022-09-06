package hexlet.code;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema {
    private boolean notEmpty;
    private int minStringLength;
    private List<String> requiredContent;

    public final void required() {
        notEmpty = true;
    }

    @Override
    public final boolean isValid(Object o) {
        String str = (String) o;
        boolean isEmpty = str == null || str.length() == 0;
        if (notEmpty & isEmpty) {
            return false;
        }
        if (!isEmpty && str.length() < minStringLength) {
            return false;
        }
        if (!isEmpty && requiredContent != null) {
            for (String s : requiredContent) {
                if (!str.contains(s)) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void minLength(int min) {
        minStringLength = min;
    }

    public final StringSchema contains(String str) {
        if (requiredContent == null) {
            requiredContent = new ArrayList<>();
        }
        requiredContent.add(str);
        return this;
    }
}
