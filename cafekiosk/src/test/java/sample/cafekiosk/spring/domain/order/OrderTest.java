package sample.cafekiosk.spring.domain.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sample.cafekiosk.spring.domain.product.Product;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.order.OrderStatus.INIT;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;


class OrderTest {

    @DisplayName("주문 생성시 상품 목록에서 주문의 총 금액을 계산한다.")
    @Test
    void calculatetotalPrice() {
        // given
        List<Product> products = List.of(
                createProduct("001", 4000),
                createProduct("002", 6000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        assertThat(order.getTotalPrice()).isEqualTo(10000);
    }

    @DisplayName("주문 생성시 주문의 상태는 INIT이다.")
    @Test
    void checkInit() {
        // given
        List<Product> products = List.of(
                createProduct("001", 4000),
                createProduct("002", 6000)
        );

        // when
        Order order = Order.create(products, LocalDateTime.now());

        // then
        // Enum의 경우 isEqualByComparingTo() 메소드로 검증이 가능하다.
//        assertThat(order.getOrderStatus()).isEqualTo(INIT);
        assertThat(order.getOrderStatus()).isEqualByComparingTo(INIT);
    }

    @DisplayName("주문 생성시 주문의 등록시간을 기록한다.")
    @Test
    void registeredDateTime() {
        // given
        List<Product> products = List.of(
                createProduct("001", 4000),
                createProduct("002", 6000)
        );

        // when
        LocalDateTime registeredDateTime = LocalDateTime.now();
        Order order = Order.create(products, registeredDateTime);

        // then
        assertThat(order.getRegisteredDateTime()).isEqualTo(registeredDateTime);
    }

    private Product createProduct(String productNumber, int price) {
        return Product.builder()
                .type(HANDMADE)
                .productNumber(productNumber)
                .price(price)
                .name("product")
                .sellingStatus(SELLING)
                .build();
    }

}