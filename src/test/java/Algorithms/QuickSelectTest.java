package Algorithms;

import Algorithms.metrics.Metrics;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSelectTest {
    @Test
    void selectsElementsFromRandomArrays() {
        Random random = new Random(42);

        for (int test = 0; test < 100; test++) {
            int size = random.nextInt(499) + 1;
            int[] values = random.ints(size, -10_000, 10_001).toArray();
            int[] sorted = values.clone();
            Arrays.sort(sorted);
            int k = random.nextInt(size);

            assertEquals(sorted[k], QuickSelect.select(values, k));
        }
    }

    @Test
    void rejectsInvalidInput() {
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(null, 0));
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(new int[0], 0));
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, -1));
        assertThrows(IllegalArgumentException.class,
                () -> QuickSelect.select(new int[]{1, 2, 3}, 3));
    }

    @Test
    void recordsMetrics() {
        Metrics metrics = new Metrics();

        assertEquals(3, QuickSelect.select(new int[]{5, 1, 4, 3, 2}, 2, metrics));
        assertTrue(metrics.getComparisons() > 0);
        assertEquals(1, metrics.getMaxDepth());
        assertTrue(metrics.getTimeNanos() > 0);
    }
}
