package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {
    private NumberSchema schema;

    @BeforeEach
    void setUp() {
        schema = new Validator().number();
    }

    @Test
    void required() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid("number")).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid("number")).isFalse();
    }

    @Test
    void positive() {
        assertThat(schema.isValid(-1)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        schema.positive();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(null)).isTrue();
    }

    @Test
    void checkRange() {
        final int min = -5;
        final int max = 5;
        assertThat(schema.isValid(Integer.MIN_VALUE)).isTrue();
        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid(Integer.MAX_VALUE)).isTrue();
        schema.range(min, max);
        for (int i = min; i <= max; i++) {
            assertThat(schema.isValid(i)).isTrue();
        }
        assertThat(schema.isValid(min - 1)).isFalse();
        assertThat(schema.isValid(max + 1)).isFalse();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
