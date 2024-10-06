package mubarak.assessment.techieplanet.programmingAndAlgorithm;

import mubarak.assessment.techieplanet.solutions.programmingAndAlgorithm.Solution3;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Solution3Test {
    @Test
    public void testZero() {
        assertEquals(0, Solution3.sumDigits(0));
    }

    @Test
    public void testSingleDigit() {
        assertEquals(5, Solution3.sumDigits(5));
    }

    @Test
    public void testMultipleDigits() {
        assertEquals(6, Solution3.sumDigits(123));
        assertEquals(15, Solution3.sumDigits(555));
        assertEquals(23, Solution3.sumDigits(1234445));
    }

    @Test
    public void testLargeNumber() {
        assertEquals(144, Solution3.sumDigits(9999999999999999L));
    }
}
