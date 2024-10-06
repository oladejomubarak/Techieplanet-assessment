package mubarak.assessment.techieplanet.programmingAndAlgorithm;

import mubarak.assessment.techieplanet.solutions.programmingAndAlgorithm.Solution1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Solution1Test {

    @Test
    public void testExactHour() {
        assertEquals("Five o'clock", Solution1.timeInWords(5, 0));
    }

    @Test
    public void testOneMinutePast() {
        assertEquals("One minute past five", Solution1.timeInWords(5, 1));
    }

    @Test
    public void testQuarterPast() {
        assertEquals("Quarter past five", Solution1.timeInWords(5, 15));
    }

    @Test
    public void testHalfPast() {
        assertEquals("Half past five", Solution1.timeInWords(5, 30));
    }

    @Test
    public void testQuarterTo() {
        assertEquals("Quarter to six", Solution1.timeInWords(5, 45));
    }

    @Test
    public void testMinutesTo() {
        assertEquals("Thirteen minutes to six", Solution1.timeInWords(5, 47));
    }

    @Test
    public void testMidnightEdgeCase() {
        assertEquals("One o'clock", Solution1.timeInWords(12, 60));
    }

}
