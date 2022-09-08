package hexlet.code.schemas;

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
    public final boolean isValid(Object object) {
        this.o = object;
        if (needNotNull && isNull()) {
            return false;
        }
        if (needMap && !isMap()) {
            return false;
        }
        if (minEntriesAmount > 0 && !hasMinEntriesAmount()) {
            return false;
        }
        if (this.schemas != null && !hasSchema()) {
            return false;
        }
        return true;
    }

    private boolean isMap() {
        if (!isNull()) {
            return this.o instanceof Map;
        }
        return false;
    }

    private boolean hasMinEntriesAmount() {
        if (!isNull() && isMap()) {
            Map<?, ?> map = (Map<?, ?>) this.o;
            return map.size() >= minEntriesAmount;
        }
        return true;
    }

    private boolean hasSchema() {
        if (!isNull() && isMap()) {
            Map<?, ?> map = (Map<?, ?>) this.o;
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
