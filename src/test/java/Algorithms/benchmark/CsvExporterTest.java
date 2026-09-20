package Algorithms.benchmark;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CsvExporterTest {
    @Test
    void writesHeaderAndResults(@TempDir Path tempDirectory) throws Exception {
        BenchmarkResult result = new BenchmarkResult(
                "QuickSort", "random", 1_000, 1.25, 12_345, 8);
        Path output = tempDirectory.resolve("nested/results.csv");

        new CsvExporter().export(List.of(result), output);

        assertEquals(List.of(
                "algorithm,input,n,time_ms,comparisons,max_depth",
                "QuickSort,random,1000,1.25,12345,8"
        ), Files.readAllLines(output));
    }
}
