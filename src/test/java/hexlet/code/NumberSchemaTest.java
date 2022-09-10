package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {
    private NumberSchema schema;
    private final double testMin = -5;
    private final double testMax = 5;
    private final double sampleNumber = 0.000001;

    @BeforeEach
    void setUp() {
        schema = new Validator().number();
    }

    @Test
    void withoutRequired() {
        assertThat(schema.isValid(null)).isTrue();

        assertThat(schema.isValid(-1)).isTrue();
        assertThat(schema.isValid(1f)).isTrue();
        assertThat(schema.isValid("number")).isTrue();
    }

    @Test
    void withRequired() {
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-1)).isTrue();
        assertThat(schema.isValid(1f)).isTrue();
        assertThat(schema.isValid("number")).isFalse();
    }

    @Test
    void withoutPositive() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(-Double.MAX_VALUE)).isTrue();
        assertThat(schema.isValid(-1)).isTrue();
        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(Double.MAX_VALUE)).isTrue();
    }

    @Test
    void withPositive() {
        schema.positive();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(-Double.MAX_VALUE)).isFalse();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
        assertThat(schema.isValid(Double.MAX_VALUE)).isTrue();
    }

    @Test
    void withRange() {
        schema.range(testMin, testMax);
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(-Double.MAX_VALUE)).isFalse();
        assertThat(schema.isValid(testMin - sampleNumber)).isFalse();
        assertThat(schema.isValid(testMin)).isTrue();
        assertThat(schema.isValid(testMin + sampleNumber)).isTrue();
        assertThat(schema.isValid(0)).isTrue();
        assertThat(schema.isValid(testMax)).isTrue();
        assertThat(schema.isValid(testMax + sampleNumber)).isFalse();
        assertThat(schema.isValid(Double.MAX_VALUE)).isFalse();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
