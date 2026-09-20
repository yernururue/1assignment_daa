package Algorithms.benchmark;

import java.util.Collections;
import java.util.List;

public class BenchmarkRunner {
    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final int REPEAT_COUNT = 5;

    private final InputGenerator inputGenerator;

    public BenchmarkRunner() {
        inputGenerator = new InputGenerator();
    }

    public List<BenchmarkResult> run() {
        // TODO: запустить каждый алгоритм на всех типах входных данных
        // TODO: сохранить медиану пяти запусков
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        // TODO: запустить benchmark и записать results.csv
    }
}
