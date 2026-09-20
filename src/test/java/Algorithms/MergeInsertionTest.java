package Algorithms;

import Algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeInsertionTest {
    @Test
    void sortsRandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int[] actual = random.ints(random.nextInt(500), -10_000, 10_001).toArray();
            int[] expected = actual.clone();
            Arrays.sort(expected);

            MergeInsertion.algorithm(actual);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    void sortsEdgeCases() {
        assertSorted(new int[0]);
        assertSorted(new int[]{7});
        assertSorted(new int[]{4, 4, 4, 4});
        assertSorted(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void recordsMetrics() {
        int[] values = {5, 2, 4, 1, 3};
        Metrics metrics = new Metrics();

        MergeInsertion.algorithm(values, metrics);

        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getMaxDepth() > 0);
        assertTrue(metrics.getTimeNanos() > 0);
    }

    private void assertSorted(int[] values) {
        int[] expected = values.clone();
        Arrays.sort(expected);
        MergeInsertion.algorithm(values);
        assertArrayEquals(expected, values);
    }
}
