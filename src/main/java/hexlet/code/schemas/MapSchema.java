package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema {
    private boolean needNotNull;
    private boolean needMap;
    private int entriesRequired;
    private Map<String, BaseSchema> schemas;

    public final MapSchema required() {
        this.needNotNull = true;
        this.needMap = true;
        return this;
    }

    public final MapSchema sizeof(int size) {
        this.entriesRequired = size;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> requiredSchemas) {
        this.schemas = requiredSchemas;
        return this;
    }

    @Override
    public final boolean isValid(Object o) {
        setObjectToValidate(o);
        return checkConditions(
                needNotNull && isNull(),
                needMap && !isMap(),
                entriesRequired > 0 && !hasMinEntriesAmount(),
                this.schemas != null && !hasSchema()
        );
    }

    private boolean isMap() {
        if (!isNull()) {
            return getObjectToValidate() instanceof Map;
        }
        return false;
    }

    private boolean hasMinEntriesAmount() {
        if (!isNull() && isMap()) {
            Map<?, ?> map = (Map<?, ?>) getObjectToValidate();
            return map.size() == entriesRequired;
        }
        return true;
    }

    private boolean hasSchema() {
        if (!isNull() && isMap()) {
            Map<?, ?> map = (Map<?, ?>) getObjectToValidate();
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
