package Algorithms.benchmark;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvExporter {
    public void export(List<BenchmarkResult> results, Path outputPath) throws IOException {
        Path parent = outputPath.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                outputPath, StandardCharsets.UTF_8)) {
            writer.write("algorithm,input,n,time_ms,comparisons,max_depth");
            writer.newLine();

            for (BenchmarkResult result : results) {
                writer.write(String.join(",",
                        result.getAlgorithm(),
                        result.getInput(),
                        Integer.toString(result.getSize()),
                        Double.toString(result.getTimeMs()),
                        Long.toString(result.getComparisons()),
                        Integer.toString(result.getMaxDepth())));
                writer.newLine();
            }
        }
    }
}
