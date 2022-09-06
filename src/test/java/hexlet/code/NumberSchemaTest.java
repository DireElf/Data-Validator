package hexlet.code;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NumberSchemaTest {
    private static NumberSchema schema;

    public static NumberSchema getSchema() {
        return schema;
    }

    public static void setSchema(NumberSchema sch) {
        schema = sch;
    }

    @BeforeEach
    void setUp() {
        schema = new Validator().number();
    }

    @Test
    void required() {
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        schema.required();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
    }

    @Test
    void positive() {
        schema.required();
        assertThat(schema.isValid(-1)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        schema.positive();
        assertThat(schema.isValid(-1)).isFalse();
        assertThat(schema.isValid(1)).isTrue();
    }

    @Test
    void checkRange() {
        schema.required();
        final int min = -5;
        final int max = 5;
        assertThat(schema.isValid(-1)).isTrue();
        assertThat(schema.isValid(1)).isTrue();
        schema.range(min, max);
        for (int i = min; i <= max; i++) {
            assertThat(schema.isValid(i)).isTrue();
        }
        final int lowerThanMin = -6;
        final int higherThanMax = 6;
        assertThat(schema.isValid(lowerThanMin)).isFalse();
        assertThat(schema.isValid(higherThanMax)).isFalse();
    }

    @AfterEach
    void tearDown() {
        schema = null;
    }
}
