package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

class ProductTypeTest {

    @DisplayName("상품의 타입이 HANDMADE라면 재고 관련 타입이 아니다.")
    @Test
    void containsStockType1() {
        // given
        ProductType givenType = HANDMADE;

        // when
        boolean result = containsStockType(givenType);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("상품의 타입이 BOTTLE, BAKERY라면 재고 관련 타입이다.")
    @Test
    void containsStockType2() {
        // given
        ProductType givenType = BOTTLE;

        // when
        boolean result = containsStockType(givenType);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @Test
    void containsStockType3() {
        // given
        ProductType givenType1 = HANDMADE;
        ProductType givenType2 = BOTTLE;
        ProductType givenType3 = BAKERLY;

        // when
        boolean result1 = containsStockType(givenType1);
        boolean result2 = containsStockType(givenType2);
        boolean result3 = containsStockType(givenType3);

        // then
        assertThat(result1).isFalse();
        assertThat(result2).isTrue();
        assertThat(result3).isTrue();
    }

    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @CsvSource({"HANDMADE, false", "BOTTLE, true", "BAKERLY, true"})
    @ParameterizedTest
    void containsStockType4(ProductType productType, boolean expected) {
        // when
        boolean result = containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideProductTypesForCheckingStockType() {
        return Stream.of(
                Arguments.of(HANDMADE, false),
                Arguments.of(BOTTLE, true),
                Arguments.of(BAKERLY, true)
        );
    }
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크한다.")
    @MethodSource("provideProductTypesForCheckingStockType")
    @ParameterizedTest
    void containsStockType5(ProductType productType, boolean expected) {
        // when
        boolean result = containsStockType(productType);

        // then
        assertThat(result).isEqualTo(expected);
    }



}