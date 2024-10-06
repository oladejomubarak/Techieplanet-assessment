package mubarak.assessment.techieplanet.programmingAndAlgorithm;

import mubarak.assessment.techieplanet.solutions.programmingAndAlgorithm.Solution2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Solution2Test {


    @Test
    public void testAllUniqueElements() {
        int[][] array = {
                {10, 20, 30, 40},
                {5, 15, 25},
                {7}
        };
        int[][] expected = {
                {10, 20, 30, 40},
                {5, 15, 25},
                {7}
        };
        Solution2.removeDuplicates(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testAllDuplicates() {
        int[][] array = {
                {2, 2, 2, 2},
                {8, 8, 8},
                {6, 6}
        };
        int[][] expected = {
                {2, 0, 0, 0},
                {8, 0, 0},
                {6, 0}
        };
        Solution2.removeDuplicates(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testEmptyArray() {
        int[][] array = {};
        int[][] expected = {};
        Solution2.removeDuplicates(array);
        assertArrayEquals(expected, array);
    }

    @Test
    public void testDifferentLengths() {
        int[][] array = {
                {1, 2, 3, 4, 5},
                {1, 2},
                {1, 2, 3},
                {1}
        };
        int[][] expected = {
                {1, 2, 3, 4, 5},
                {1, 2},
                {1, 2, 3},
                {1}
        };
        Solution2.removeDuplicates(array);
        assertArrayEquals(expected, array);
    }


    @Test
    public void testSingleElementSubArrays() {
        int[][] array = {
                {1},
                {2},
                {3},
                {4},
                {5}
        };
        int[][] expected = {
                {1},
                {2},
                {3},
                {4},
                {5}
        };
        Solution2.removeDuplicates(array);
        assertArrayEquals(expected, array);
    }

}
