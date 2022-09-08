package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {
    private static StringSchema schema;

    public static StringSchema getSchema() {
        return schema;
    }

    public static void setSchema(StringSchema sch) {
        StringSchemaTest.schema = sch;
    }

    @BeforeEach
    void setUp() {
        schema = new Validator().string();
    }

    @Test
    void required() {
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        schema = schema.required();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("what does the fox say")).isTrue();
    }

    @Test
    void contains() {
        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isFalse();
    }

    @Test
    void minLength() {
        final int sampleLength = 4;
        schema = schema.minLength(sampleLength);
        assertThat(schema.isValid("what")).isTrue();
        assertThat(schema.isValid("what ")).isTrue();
        assertThat(schema.isValid("h")).isFalse();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
