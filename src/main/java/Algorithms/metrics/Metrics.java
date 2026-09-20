package Algorithms.metrics;

public class Metrics {
    private long comparisons;
    private int maxDepth;
    private long timeNanos;

    public Metrics() {
        comparisons = 0;
        maxDepth = 0;
        timeNanos = 0;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public long getTimeNanos() {
        return timeNanos;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void updateMaxDepth(int depth) {
        maxDepth = Math.max(maxDepth, depth);
    }

    public void setTimeNanos(long timeNanos) {
        this.timeNanos = timeNanos;
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
        timeNanos = 0;
    }
}
