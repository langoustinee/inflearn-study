package sample.cafekiosk.unit;

import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.Lette;
import sample.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;

public class CafeKioskRunner {
    public static void main(String[] args) {
        CafeKiosk cafeKiosk = new CafeKiosk();

        cafeKiosk.add(new Americano());
        System.out.println(">>> 아메리카노 추가");

        cafeKiosk.add(new Lette());
        System.out.println(">>> 라떼 추가");

        int totalPrice = cafeKiosk.calculrateTotalPrice();
        System.out.println(">>> 총 주문 가격: " + totalPrice);

        Order order = cafeKiosk.createOrder(LocalDateTime.now());

    }
}
