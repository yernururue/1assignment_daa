package Algorithms.metrics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MetricsTest {
    @Test
    void updatesAndResetsAllValues() {
        Metrics metrics = new Metrics();

        metrics.incrementComparisons();
        metrics.incrementComparisons();
        metrics.updateMaxDepth(3);
        metrics.updateMaxDepth(2);
        metrics.setTimeNanos(500);

        assertEquals(2, metrics.getComparisons());
        assertEquals(3, metrics.getMaxDepth());
        assertEquals(500, metrics.getTimeNanos());

        metrics.reset();

        assertEquals(0, metrics.getComparisons());
        assertEquals(0, metrics.getMaxDepth());
        assertEquals(0, metrics.getTimeNanos());
    }
}
