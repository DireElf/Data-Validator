package hexlet.code.schemas;

import java.util.Map;
import java.util.Set;

public class MapSchema extends BaseSchema {
    public final MapSchema required() {
        addCheck(x -> x instanceof Map);
        return this;
    }

    public final MapSchema sizeof(int size) {
        addCheck(x -> x instanceof Map && ((Map<?, ?>) x).size() == size);
        return this;
    }

    public final MapSchema shape(Map<String, BaseSchema> requiredSchemas) {
        addCheck(x -> x instanceof Map && hasShape(x, requiredSchemas));
        return this;
    }

    private boolean hasShape(Object o, Map<String, BaseSchema> schemas) {
        Map<?, ?> map = (Map<?, ?>) o;
        Set<?> keys = map.keySet();
        for (Object key : keys) {
            if (schemas.containsKey(key)) {
                BaseSchema schema = schemas.get(key);
                if (!schema.isValid(map.get(key))) {
                    return false;
                }
            }
        }
        return true;
    }
}
