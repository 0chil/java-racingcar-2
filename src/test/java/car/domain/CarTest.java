package car.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CarTest {

    @Test
    @DisplayName("이름이 있는지 확인하는 테스트")
    void 이름을_가진다() {
        Car car = new Car("물떡");

        assertThat(car.getName()).isEqualTo("물떡");
    }

    @Test
    @DisplayName("위치가 있는지 확인하는 테스트")
    void 위치를_가진다() {
        Car car = new Car("물떡", 2);

        assertThat(car.getPosition()).isEqualTo(2);
    }

    @ParameterizedTest(name = "움직일 수 있는지 확인하는 테스트")
    @CsvSource(value = {"true:1", "false:0"}, delimiter = ':')
    void 움직일_수_있으면_전진하고_아니면_정지한다(boolean isMovable, int result) {
        Car car = new Car("물떡");
        car.move(() -> isMovable);
        assertThat(car.getPosition()).isEqualTo(result);
    }

    @Test
    @DisplayName("위치는 시작점보다 작은지 확인하는 테스트")
    void 위치는_시작점보다_작으면_안된다() {
        assertThatThrownBy(() -> new Car("포비", -1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 이름이_같으면_동일한_차로_간주한다() {
        assertThat(new Car("이름")).isEqualTo(new Car("이름"));
    }

    @Test
    void 이름이_다르면_다른_차량이다() {
        assertThat(new Car("이름")).isNotEqualTo(new Car("다른 이름"));
    }
}