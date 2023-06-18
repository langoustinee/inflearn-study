package sample.cafekiosk.unit.beverage;

public class Lette implements Beverage {

    @Override
    public String getName() {
        return "라떼";
    }

    @Override
    public int getPrice() {
        return 6000;
    }
}
