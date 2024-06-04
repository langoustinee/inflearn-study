package com.example.sample;

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
        String[] result = calculationRequestReader.read();

        // then
        assertEquals(result[0], "2");
        assertEquals(result[1], "+");
        assertEquals(result[2], "3");
    }
}
