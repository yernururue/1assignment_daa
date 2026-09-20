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
        // TODO: посчитать сравнение элементов
    }

    public void updateMaxDepth(int depth) {
        // TODO: обновить максимальную глубину
    }

    public void setTimeNanos(long timeNanos) {
        // TODO: сохранить время выполнения
    }

    public void reset() {
        // TODO: сбросить метрики перед новым запуском
    }
}
