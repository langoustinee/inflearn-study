package com.example.sample;

import org.example.sample.CalculationRequest;
import org.example.sample.CalculationRequestReader;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

public class CalculationRequestReaderTest {

    @Test
    public void System_in으로_데이터를_읽어올_수_있다() {
        // given
        CalculationRequestReader calculationRequestReader = new CalculationRequestReader();

        // when
        System.setIn(new ByteArrayInputStream("2 + 3".getBytes()));
        CalculationRequest result = calculationRequestReader.read();

        // then
        assertEquals(result.getNum1(), 2);
        assertEquals(result.getOperator(), "+");
        assertEquals(result.getNum2(), 3);
    }
}
