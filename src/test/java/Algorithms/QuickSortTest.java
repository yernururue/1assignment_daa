package Algorithms;

import Algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSortTest {
    @Test
    void sortsRandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int[] actual = random.ints(random.nextInt(500), -10_000, 10_001).toArray();
            int[] expected = actual.clone();
            Arrays.sort(expected);

            QuickSort.algorithm(actual);

            assertArrayEquals(expected, actual);
        }
    }

    @Test
    void sortsArraysWithDuplicates() {
        Random random = new Random(42);
        int[] actual = random.ints(10_000, 0, 10).toArray();
        int[] expected = actual.clone();
        Arrays.sort(expected);

        QuickSort.algorithm(actual);

        assertArrayEquals(expected, actual);
    }

    @Test
    void sortsEdgeCases() {
        assertSorted(new int[0]);
        assertSorted(new int[]{7});
        assertSorted(new int[]{4, 4, 4, 4});
        assertSorted(new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void keepsRecursionDepthBounded() {
        int size = 100_000;
        int[] values = new int[size];
        Arrays.setAll(values, index -> index);
        Metrics metrics = new Metrics();

        QuickSort.algorithm(values, metrics);

        int depthLimit = 2 * (int) Math.ceil(Math.log(size) / Math.log(2));
        assertTrue(metrics.getMaxDepth() <= depthLimit,
                () -> "depth " + metrics.getMaxDepth() + " exceeds " + depthLimit);
        assertTrue(metrics.getComparisons() > 0);
        assertTrue(metrics.getTimeNanos() > 0);
    }

    private void assertSorted(int[] values) {
        int[] expected = values.clone();
        Arrays.sort(expected);
        QuickSort.algorithm(values);
        assertArrayEquals(expected, values);
    }
}
