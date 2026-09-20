package Algorithms.benchmark;

import Algorithms.MergeInsertion;
import Algorithms.QuickSelect;
import Algorithms.QuickSort;
import Algorithms.metrics.Metrics;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BenchmarkRunner {
    private static final int[] SIZES = {1_000, 10_000, 100_000, 1_000_000};
    private static final int REPEAT_COUNT = 5;
    private static final String[] INPUT_TYPES = {"random", "sorted", "duplicates"};
    private static final String[] ALGORITHMS = {
            "MergeInsertion", "QuickSort", "QuickSelect"
    };

    private final InputGenerator inputGenerator;
    private final int[] sizes;
    private final int repeatCount;

    public BenchmarkRunner() {
        this(new InputGenerator(), SIZES, REPEAT_COUNT);
    }

    BenchmarkRunner(InputGenerator inputGenerator, int[] sizes, int repeatCount) {
        this.inputGenerator = inputGenerator;
        this.sizes = sizes.clone();
        this.repeatCount = repeatCount;
    }

    public List<BenchmarkResult> run() {
        List<BenchmarkResult> results = new ArrayList<>();

        for (String inputType : INPUT_TYPES) {
            for (int size : sizes) {
                int[][] samples = generateSamples(inputType, size);
                for (String algorithm : ALGORITHMS) {
                    results.add(runCase(algorithm, inputType, size, samples));
                }
            }
        }

        return results;
    }

    private int[][] generateSamples(String inputType, int size) {
        int[][] samples = new int[repeatCount][];
        for (int run = 0; run < repeatCount; run++) {
            samples[run] = switch (inputType) {
                case "random" -> inputGenerator.generateRandom(size);
                case "sorted" -> inputGenerator.generateSorted(size);
                case "duplicates" -> inputGenerator.generateDuplicates(size);
                default -> throw new IllegalArgumentException("Unknown input type: " + inputType);
            };
        }
        return samples;
    }

    private BenchmarkResult runCase(String algorithm, String inputType,
                                    int size, int[][] samples) {
        List<BenchmarkResult> runs = new ArrayList<>(repeatCount);

        for (int[] sample : samples) {
            int[] values = sample.clone();
            Metrics metrics = new Metrics();

            switch (algorithm) {
                case "MergeInsertion" -> MergeInsertion.algorithm(values, metrics);
                case "QuickSort" -> QuickSort.algorithm(values, metrics);
                case "QuickSelect" -> QuickSelect.select(values, size / 2, metrics);
                default -> throw new IllegalArgumentException("Unknown algorithm: " + algorithm);
            }

            runs.add(new BenchmarkResult(
                    algorithm,
                    inputType,
                    size,
                    metrics.getTimeNanos() / 1_000_000.0,
                    metrics.getComparisons(),
                    metrics.getMaxDepth()));
        }

        runs.sort(Comparator.comparingDouble(BenchmarkResult::getTimeMs));
        return runs.get(runs.size() / 2);
    }

    public static void main(String[] args) throws IOException {
        List<BenchmarkResult> results = new BenchmarkRunner().run();
        Path output = Path.of("results.csv");
        new CsvExporter().export(results, output);
        System.out.println("Wrote " + results.size() + " benchmark rows to " + output);
    }
}
