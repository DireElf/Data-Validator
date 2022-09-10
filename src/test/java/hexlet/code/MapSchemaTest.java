package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {
    private MapSchema schema;
    private final Map<Object, Object> testMap = Map.of("test", new int[]{1, 2});
    private static final Map<String, BaseSchema> SCHEMAS = new HashMap<>();

    @BeforeAll
    static void setSchemas() {
        Validator v = new Validator();
        SCHEMAS.put("name", v.string().required());
        SCHEMAS.put("age", v.number().positive());
    }

    @BeforeEach
    void setUp() {
        schema = new Validator().map();
    }

    @Test
    void withoutRequired() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("test")).isTrue();
        assertThat(schema.isValid(Long.MIN_VALUE)).isTrue();
        assertThat(schema.isValid(testMap)).isTrue();
    }

    @Test
    void withRequired() {
        schema.required();
        assertThat(schema.isValid(new HashMap<>())).isTrue();
        assertThat(schema.isValid(testMap)).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(Long.MIN_VALUE)).isFalse();
        assertThat(schema.isValid("test")).isFalse();
    }

    @Test
    void sizeOf() {
        schema.sizeof(1);
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        assertThat(schema.isValid(testMap)).isTrue();
        assertThat(schema.isValid(SCHEMAS)).isFalse();
    }

    @Test
    void schemas() {
        schema.shape(SCHEMAS);
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 1);
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -1);
        assertThat(schema.isValid(human4)).isFalse();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
