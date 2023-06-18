package sample.cafekiosk.unit.beverage;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @Test
    void getName() {
        Americano americano = new Americano();

        // JUnit의 메서드가 아닌 AssertJ의 메서드 사용하여 보다 가독성 좋은 테스트 코드를 작성할 수 있다.
        //        assertEquals(americano.getName(), "아메리카노");
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @Test
    void getPrice() {
        Americano americano = new Americano();
        assertThat(americano.getPrice()).isEqualTo(4000);
    }

}