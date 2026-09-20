package Algorithms.benchmark;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BenchmarkRunnerTest {
    @Test
    void runsEveryAlgorithmInputAndSizeCombination() {
        BenchmarkRunner runner = new BenchmarkRunner(
                new InputGenerator(42), new int[]{10, 100}, 3);

        List<BenchmarkResult> results = runner.run();

        assertEquals(18, results.size());
        assertEquals(Set.of("MergeInsertion", "QuickSort", "QuickSelect"),
                results.stream()
                        .map(BenchmarkResult::getAlgorithm)
                        .collect(Collectors.toSet()));
        assertEquals(Set.of("random", "sorted", "duplicates"),
                results.stream()
                        .map(BenchmarkResult::getInput)
                        .collect(Collectors.toSet()));
        assertTrue(results.stream().allMatch(result -> result.getTimeMs() >= 0));
        assertTrue(results.stream().allMatch(result -> result.getComparisons() > 0));
        assertTrue(results.stream().allMatch(result -> result.getMaxDepth() > 0));
    }
}
