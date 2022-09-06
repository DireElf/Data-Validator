package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MapSchemaTest {
    private static MapSchema schema;
    private static Map<Object, Object> testMap;

    public static MapSchema getSchema() {
        return schema;
    }
    public static void setSchema(MapSchema sch) {
        schema = sch;
    }

    @BeforeEach
    void setUp() {
        schema = new Validator().map();
        testMap = Map.of("test", new int[]{1, 2}, "test1", new int[]{1, 2});
    }

    @Test
    void required() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid("test")).isTrue();
        schema.required();
        assertThat(schema.isValid(testMap)).isTrue();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("test")).isFalse();
    }

    @Test
    void sizeOf() {
        schema.sizeOf(1);
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        assertThat(schema.isValid(Map.of("test", 1))).isTrue();
        assertThat(schema.isValid(testMap)).isTrue();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
