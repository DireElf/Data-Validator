package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;

public class StringSchema extends BaseSchema {
    private int minStringLength;
    private List<String> requiredContent;

    public final StringSchema required() {
        addCheck("isRequired");
        return this;
    }

    public final StringSchema minLength(int min) {
        addCheck("isLengthValid");
        this.minStringLength = min;
        return this;
    }

    public final StringSchema contains(String s) {
        addCheck("hasStrings");
        if (this.requiredContent == null) {
            this.requiredContent = new ArrayList<>();
        }
        this.requiredContent.add(s);
        return this;
    }

    private boolean isRequired(Object o) {
        return o instanceof String && !((String) o).isEmpty();
    }

    private boolean isLengthValid(Object o) {
        return o instanceof String && ((String) o).length() >= this.minStringLength;
    }

    private boolean hasStrings(Object o) {
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
