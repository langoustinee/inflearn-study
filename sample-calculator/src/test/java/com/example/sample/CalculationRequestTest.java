package com.example.sample;

import org.example.sample.BadRequestException;
import org.example.sample.CalculationRequest;
import org.example.sample.InvalidOperatorException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculationRequestTest {

    @Test
    public void 유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = {"2", "+", "3"};
        // when
        CalculationRequest calculationRequest = new CalculationRequest(parts);
        // then
        assertEquals(calculationRequest.getNum1(), 2);
        assertEquals(calculationRequest.getOperator(), "+");
        assertEquals(calculationRequest.getNum2(), 3);
    }

    @Test
    public void 세자리_숫자가_넘어가는_유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = {"102", "+", "103"};
        // when
        CalculationRequest calculationRequest = new CalculationRequest(parts);
        // then
        assertEquals(calculationRequest.getNum1(), 102);
        assertEquals(calculationRequest.getOperator(), "+");
        assertEquals(calculationRequest.getNum2(), 103);
    }

    @Test
    public void 유효한_길이의_숫자가_들어오지_않을_경우_예외가_발생한다() {
        // given
        String[] parts = {"102", "+"};
        // when & then
        assertThrows(BadRequestException.class, () -> {
            new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_연산가_들어오면_예외가_발생한다() {
        // given
        String[] parts = {"102", "x", "3"};
        // when & then
        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_길이의_연산가_들어오면_예외가_발생한다() {
        // given
        String[] parts = {"102", "++", "3"};
        // when & then
        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(parts);
        });
    }

}
