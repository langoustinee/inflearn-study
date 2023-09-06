package sample.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.SELLING;
import static sample.cafekiosk.spring.domain.product.ProductType.HANDMADE;

class ProductNumberFactoryTest extends IntegrationTestSupport {

    @Autowired
    private ProductNumberFactory productNumberFactory;
    
    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("이미 등록된 상품이 있다면, 가장 최신 상품번호에서 1이 증가된 상풍번호를 만든다.")
    @Test
    void createNextProductNumber() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, SELLING, "카푸치노", 7000);
        Product product3 = createProduct("003", HANDMADE, SELLING, "콜드브루", 5000);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        String lastNumber = productNumberFactory.createNextProductNumber();

        // then
        assertThat(lastNumber).isEqualTo("004");
    }

    @DisplayName("등록된 상품이 없을 경우, '001'라는 상품번호를 만든다.")
    @Test
    void createNextProductNumberProductIsEmpty() {
        // given

        // when
        String lastNumber = productNumberFactory.createNextProductNumber();

        // then
        assertThat(lastNumber).isEqualTo("001");
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }

}