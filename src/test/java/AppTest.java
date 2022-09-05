import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    @Test
    void sample() {
        assertThat(App.sample()).isEqualTo(0);
    }
}
