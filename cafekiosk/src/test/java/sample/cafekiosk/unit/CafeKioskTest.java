package sample.cafekiosk.unit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Lette;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CafeKioskTest {

    @DisplayName("음료를 추가하면 음료 수와 음료 종류가 출력된다.")
    @Test
    void add_manual_test() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        System.out.println(">>> 담긴 음료 수: " + cafeKiosk.getBeverages().size());
        System.out.println(">>> 담긴 음료 종류: " + cafeKiosk.getBeverages().get(0).getName());
    }

    @DisplayName("음료를 추가하면 주문 목록에 담긴다.")
    @Test
    void add() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        cafeKiosk.add(new Americano());

        // List의 사이즈를 체크하는 AssertJ의 메서드 hasSize() 사용
//        assertThat(cafeKiosk.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);
        assertThat(cafeKiosk.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("음료 여러 잔을 추가하면 주문 목록에 추가한 음료가 모두 담긴다.")
    @Test
    void addSeveralBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano, 3);

        assertThat(cafeKiosk.getBeverages().get(0)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(1)).isEqualTo(americano);
        assertThat(cafeKiosk.getBeverages().get(2)).isEqualTo(americano);
    }

    @DisplayName("어떤 음료가 0잔 추가되면, 에러 메세지를 출력한다.")
    @Test
    void addZeroBeverages() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        // 0 이하의 경계값을 통한 예외 케이스 검증하기
        assertThatThrownBy(() -> cafeKiosk.add(americano, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("음료는 최소 1잔이상 주문할 수 있습니다.");
    }

    @DisplayName("음료 1잔을 추가한 후 취소하면 주문 목록은 비어있다.")
    @Test
    void remove() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();

        cafeKiosk.add(americano);
        assertThat(cafeKiosk.getBeverages()).hasSize(1);

        // List가 비었는지를 체크할 AssertJ의 isEmpty() 메서드 사용
        cafeKiosk.remove(americano);
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @DisplayName("여러 음료를 추가한 후 전체 취소하면 주문 목록은 비어있다.")
    @Test
    void clear() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Lette lette = new Lette();

        cafeKiosk.add(americano);
        cafeKiosk.add(lette);
        assertThat(cafeKiosk.getBeverages()).hasSize(2);

        cafeKiosk.clear();
        assertThat(cafeKiosk.getBeverages()).isEmpty();
    }

    @DisplayName("주문 목록에 담긴 아메리카노와 라떼의 총 가격은 10,000원이다.")
    @Test
    void calculrateTotalPrice() {
        // given
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        Lette lette = new Lette();

        cafeKiosk.add(americano);
        cafeKiosk.add(lette);

        // when
        int totalPrice = cafeKiosk.calculrateTotalPrice();

        // then
        assertThat(totalPrice).isEqualTo(10000);
    }

    @DisplayName("음료를 추가하고 주문하면 해당 음료가 담긴 주문이 생성된다.")
    @Test
    void createOrder() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        Order order = cafeKiosk.createOrder();

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("영업시간이 시작되면 음료를 주문할 수 있다.")
    @Test
    void createOrderWithCurrentTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        // 경계값을 통한 해피 케이스 작성
        Order order = cafeKiosk.createOrder(LocalDateTime.of(2023, 6, 20, 10, 0));

        assertThat(order.getBeverages()).hasSize(1);
        assertThat(order.getBeverages().get(0).getName()).isEqualTo("아메리카노");
    }

    @DisplayName("영업이 시작되기 전이라면 주문할 수 없다.")
    @Test
    void createOrderOutsideOpenTime() {
        CafeKiosk cafeKiosk = new CafeKiosk();
        Americano americano = new Americano();
        cafeKiosk.add(americano);

        // 경계값을 통한 해피 케이스 작성
        assertThatThrownBy(() -> cafeKiosk.createOrder(LocalDateTime.of(20203, 6, 20, 23, 0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지금은 주문할 수 없는 시간입니다.");
    }
}