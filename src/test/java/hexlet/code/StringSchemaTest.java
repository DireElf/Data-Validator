package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringSchemaTest {
    private StringSchema schema;
    private final String testString = "what does the fox say";

    @BeforeEach
    void setUp() {
        schema = new Validator().string();
    }

    @Test
    void withoutRequired() {
        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(testString)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
    }

    @Test
    void withRequired() {
        schema.required();
        assertThat(schema.isValid(testString)).isTrue();
        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(1)).isFalse();
    }

    @Test
    void containsCorrectSubstring() {
        assertThat(schema.contains("").isValid(testString)).isTrue();
        assertThat(schema.contains(" ").isValid(testString)).isTrue();
        assertThat(schema.contains("wh").isValid(testString)).isTrue();
        assertThat(schema.contains("what").isValid(testString)).isTrue();
    }

    @Test
    void containsIncorrectSubstring() {
        assertThat(schema.contains(" what").isValid(testString)).isFalse();
        assertThat(schema.contains("say ").isValid(testString)).isFalse();
        assertThat(schema.contains("whatthe").isValid(testString)).isFalse();
        assertThat(schema.isValid(testString)).isFalse();
    }

    @Test
    void minLength() {
        assertThat(schema.isValid("wha")).isTrue();
        final int testLength = 4;
        schema.minLength(testLength);
        assertThat(schema.isValid("what")).isTrue();
        assertThat(schema.isValid("what ")).isTrue();
        assertThat(schema.isValid("wha")).isFalse();
        schema.minLength(testLength - 1);
        assertThat(schema.isValid("wh")).isFalse();
        assertThat(schema.isValid("wha")).isFalse();
        assertThat(schema.isValid("what")).isTrue();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
