package Algorithms.benchmark;

public class BenchmarkResult {
    private final String algorithm;
    private final String input;
    private final int size;
    private final double timeMs;
    private final long comparisons;
    private final int maxDepth;

    public BenchmarkResult(String algorithm, String input, int size,
                           double timeMs, long comparisons, int maxDepth) {
        this.algorithm = algorithm;
        this.input = input;
        this.size = size;
        this.timeMs = timeMs;
        this.comparisons = comparisons;
        this.maxDepth = maxDepth;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getInput() {
        return input;
    }

    public int getSize() {
        return size;
    }

    public double getTimeMs() {
        return timeMs;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}
