package hexlet.code;

import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema {
    private boolean needNotNull;
    private boolean needMap;
    private int minEntriesAmount;
    private Map<String, BaseSchema> schemas;

    public final MapSchema required() {
        this.needNotNull = true;
        this.needMap = true;
        return this;
    }

    public final MapSchema sizeOf(int size) {
        this.minEntriesAmount = size;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> requiredSchemas) {
        this.schemas = requiredSchemas;
        return this;
    }

    @Override
    public final boolean isValid(Object o) {
        if (needNotNull && isNull(o)) {
            return false;
        }
        if (needMap && !isMap(o)) {
            return false;
        }
        if (minEntriesAmount > 0 && !hasMinEntriesAmount(o)) {
            return false;
        }
        if (this.schemas != null && !hasSchema(o)) {
            return false;
        }
        return true;
    }

    private boolean isMap(Object o) {
        if (!isNull(o)) {
            return o instanceof Map;
        }
        return false;
    }

    private boolean hasMinEntriesAmount(Object o) {
        if (!isNull(o) && isMap(o)) {
            Map<?, ?> map = (Map<?, ?>) o;
            return map.size() >= minEntriesAmount;
        }
        return true;
    }

    private boolean hasSchema(Object o) {
        if (!isNull(o) && isMap(o)) {
            Map<?, ?> map = (Map<?, ?>) o;
            Set<?> keys = map.keySet();
            for (Object key : keys) {
                if (this.schemas.containsKey(key)) {
                    BaseSchema schema = this.schemas.get(key);
                    if (!schema.isValid(map.get(key))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
