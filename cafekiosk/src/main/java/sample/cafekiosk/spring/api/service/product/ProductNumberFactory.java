package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@RequiredArgsConstructor
@Component
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    public String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();

        // 등록된 상품이 없을 경우 "001"로 등록한다.
        if(latestProductNumber == null) {
            return "001";
        }

        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumberInt + 1;

        // 9 -> 009 변환
        return String.format("%03d", nextProductNumberInt);
    }

}
