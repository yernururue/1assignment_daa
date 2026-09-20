package Algorithms.benchmark;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputGeneratorTest {
    @Test
    void generatesReproducibleRandomValuesWithASeed() {
        int[] first = new InputGenerator(42).generateRandom(100);
        int[] second = new InputGenerator(42).generateRandom(100);

        assertArrayEquals(first, second);
        assertEquals(100, first.length);
        assertTrue(Arrays.stream(first).distinct().count() > 1);
    }

    @Test
    void generatesSortedValues() {
        assertArrayEquals(new int[]{0, 1, 2, 3, 4},
                new InputGenerator().generateSorted(5));
    }

    @Test
    void generatesValuesFromZeroToNine() {
        int[] values = new InputGenerator(42).generateDuplicates(1_000);

        assertTrue(Arrays.stream(values).allMatch(value -> value >= 0 && value <= 9));
    }

    @Test
    void rejectsNegativeSizes() {
        assertThrows(IllegalArgumentException.class,
                () -> new InputGenerator().generateRandom(-1));
    }
}
