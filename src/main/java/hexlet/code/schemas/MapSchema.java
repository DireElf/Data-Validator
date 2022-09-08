package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema {
    private boolean hasRequired;
    private boolean hasRequiredSize;
    private boolean hasSchema;
    private int requiredSize;
    private Map<String, BaseSchema> schemas;

    public final MapSchema required() {
        this.hasRequired = true;
        return this;
    }

    public final MapSchema sizeof(int size) {
        this.hasRequiredSize = true;
        this.requiredSize = size;
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> requiredSchemas) {
        this.hasSchema = true;
        this.schemas = requiredSchemas;
        return this;
    }

    @Override
    public final boolean isValid(Object o) {
        return checkConditions(isRequired(o), isValidSize(o), hasShape(o));
    }

    private boolean isRequired(Object o) {
        if (!hasRequired) {
            return true;
        }
        if (o == null) {
            return false;
        }
        return o instanceof Map;
    }

    private boolean isValidSize(Object o) {
        if (!hasRequiredSize) {
            return true;
        }
        if (isRequired(o)) {
            Map<?, ?> map = (Map<?, ?>) o;
            return map.size() == requiredSize;
        }
        return false;
    }

    private boolean hasShape(Object o) {
        if (!hasSchema) {
            return true;
        }
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
