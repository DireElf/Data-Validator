package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    private boolean notNull;
    private boolean needMap;
    private int minEntriesAmount;

    @Override
    public final boolean isValid(Object o) {
        boolean isNull = o == null;
        if (notNull && isNull) {
            return false;
        }
        boolean isMap = o instanceof Map;
        if (!isNull && needMap && !isMap) {
            return false;
        }
        if (!isNull && isMap) {
            Map<Object, Object> map = (Map<Object, Object>) o;
            return map.size() >= minEntriesAmount;
        }
        return true;
    }

    public final void required() {
        notNull = true;
        needMap = true;
    }

    public final void sizeOf(int size) {
        minEntriesAmount = size;
    }
}
