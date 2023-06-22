package sample.cafekiosk.unit;

import lombok.Getter;
import sample.cafekiosk.unit.beverage.Beverage;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    private static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    private static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);

    private final List<Beverage> beverages = new ArrayList<>();

    public void add(Beverage beverage) {
        beverages.add(beverage);
    }

    public void add(Beverage beverage, int count) {
        if(count <= 0) {
            throw new IllegalArgumentException("음료는 최소 1잔이상 주문할 수 있습니다.");
        }
        for(int i=0; i<count; i++) {
            beverages.add(beverage);
        }
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }

    public int calculrateTotalPrice() {
        // TDD에서 테스트 통과만을 위한 Green 코드
        // return 10000;

        // 구현부 코드 1차 개선
//        int totalPrice = 0;
//        for(Beverage beverage : beverages) {
//            totalPrice += beverage.getPrice();
//        }
//        return totalPrice;

        // TDD를 통한 구현부 코드 2차 개선
        return beverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }

    public Order createOrder() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("지금은 주문할 수 없는 시간입니다.");
        }
        return new Order(currentDateTime, beverages);
    }

    public Order createOrder(LocalDateTime currentDateTime) {
        // 시간 데이터를 외부에서 받아와서 확인하도록 변경
//        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("지금은 주문할 수 없는 시간입니다.");
        }
        return new Order(currentDateTime, beverages);
    }

}
