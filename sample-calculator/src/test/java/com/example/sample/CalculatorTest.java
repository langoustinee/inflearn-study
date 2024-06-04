package com.example.sample;

import org.example.sample.Calculator;
import org.example.sample.InvalidOperatorException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTest {

    @Test
    public void 덧셈_연산을_할_수_있다() {
        // given
        long num1 = 2;
        String operator = "+";
        long num2 = 3;
        Calculator calculator = new Calculator();
        // when
        long result = calculator.calculate(num1, operator, num2);
        // then
        assertEquals(result, 5);
    }

    @Test
    public void 뺄셈_연산을_할_수_있다() {
        // given
        long num1 = 3;
        String operator = "-";
        long num2 = 2;
        Calculator calculator = new Calculator();
        // when
        long result = calculator.calculate(num1, operator, num2);
        // then
        assertEquals(result, 1);
    }

    @Test
    public void 곱셈_연산을_할_수_있다() {
        // given
        long num1 = 2;
        String operator = "*";
        long num2 = 3;
        Calculator calculator = new Calculator();
        // when
        long result = calculator.calculate(num1, operator, num2);
        // then
        assertEquals(result, 6);
    }

    @Test
    public void 나눗셈_연산을_할_수_있다() {
        // given
        long num1 = 6;
        String operator = "/";
        long num2 = 3;
        Calculator calculator = new Calculator();
        // when
        long result = calculator.calculate(num1, operator, num2);
        // then
        assertEquals(result, 2);
    }

    @Test
    public void 잘못된_연산자가_요청으로_들어올_경우_예외가_발생한다() {
        // given
        long num1 = 2;
        String operator = "@";
        long num2 = 3;
        Calculator calculator = new Calculator();
        // when & then
        assertThrows(InvalidOperatorException.class, () -> {
            calculator.calculate(num1, operator, num2);
        });
    }

}
