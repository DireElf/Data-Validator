package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {
    private StringSchema schema;
    private final String testString = "what does the fox say";
    private final int testLength1 = 4;
    private final int testLength2 = 50;

    @BeforeEach
    void setUp() {
        schema = new Validator().string();
    }

    @Test
    void withoutRequired() {
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
    }

    @Test
    void withRequired() {
        schema.required();
        assertThat(schema.isValid("what does the fox say")).isTrue();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(1)).isFalse();
    }

    @Test
    void containsCorrectSubstring() {
        assertThat(schema.contains("").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains(" ").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("wh").isValid("what does the fox say")).isTrue();
        assertThat(schema.contains("what").isValid("what does the fox say")).isTrue();
    }

    @Test
    void containsIncorrectSubstring() {
        assertThat(schema.contains(" what").isValid("what does the fox say")).isFalse();
        assertThat(schema.contains("say ").isValid("what does the fox say")).isFalse();
        assertThat(schema.contains("whatthe").isValid("what does the fox say")).isFalse();
        assertThat(schema.isValid("what does the fox say")).isFalse();
    }

    @Test
    void minLength() {
        assertThat(schema.isValid("wha")).isTrue();
        schema.minLength(testLength1);
        assertThat(schema.isValid("what")).isTrue();
        assertThat(schema.isValid("what ")).isTrue();
        assertThat(schema.isValid("wha")).isFalse();
        schema.minLength(testLength1 - 1);
        assertThat(schema.isValid("wh")).isFalse();
        assertThat(schema.isValid("wha")).isTrue();
        assertThat(schema.isValid("what")).isTrue();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
