package hexlet.code;

import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema {
    private boolean notNull;
    private boolean needMap;
    private int minEntriesAmount;
    private Map<String, BaseSchema> schemas;

    public final MapSchema required() {
        this.notNull = true;
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
    public final void check(Object o) {
        boolean isNull = o == null;
        if (notNull && isNull) {
            getValidationResults().add(false);
            return;
        }
        boolean isMap = o instanceof Map;
        if (!isNull && needMap && !isMap) {
            getValidationResults().add(false);
            return;
        }
        if (!isNull && isMap) {
            Map<?, ?> map = (Map<?, ?>) o;
            if (this.schemas != null) {
                Set<?> keys = map.keySet();
                for (Object key : keys) {
                    if (this.schemas.containsKey(key)) {
                        BaseSchema schema = this.schemas.get(key);
                        if (!schema.isValid(map.get(key))) {
                            getValidationResults().add(false);
                            return;
                        }
                    }
                }
            }
            if (map.size() < minEntriesAmount) {
                getValidationResults().add(false);
            }
        }
    }
}
