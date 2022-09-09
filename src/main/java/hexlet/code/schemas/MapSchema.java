package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema {
    private int requiredSize;
    private Map<String, BaseSchema> schemas;

    public final MapSchema required() {
        addCheck("isRequired");
        return this;
    }

    public final MapSchema sizeof(int size) {
        addCheck("isValidSize");
        this.requiredSize = size;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> requiredSchemas) {
        addCheck("hasShape");
        this.schemas = requiredSchemas;
        return this;
    }

    private boolean isRequired(Object o) {
        if (o == null) {
            return false;
        }
        return o instanceof Map;
    }

    private boolean isValidSize(Object o) {
        if (isRequired(o)) {
            Map<?, ?> map = (Map<?, ?>) o;
            return map.size() == requiredSize;
        }
        return false;
    }

    private boolean hasShape(Object o) {
        if (!isRequired(o)) {
            return false;
        }
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
        return true;
    }
}
