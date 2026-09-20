# Divide-and-Conquer Sorting and Selection

This Maven project implements three integer-array algorithms for Assignment 1:

- `MergeInsertion`: MergeSort with one reusable buffer and Insertion Sort for
  segments of at most 15 elements.
- `QuickSort`: randomized three-way partitioning with recursion on the smaller
  side and iteration over the larger side.
- `QuickSelect`: iterative selection of the zero-based k-th smallest element,
  reusing the same three-way partition as QuickSort.

Every algorithm accepts a `Metrics` object that records element comparisons,
maximum recursion depth, and elapsed time in nanoseconds.

## Requirements

- Java 17 or newer
- Maven 3.9 or newer
- Python 3.8 or newer for plots

## Build and test

Run all JUnit 5 tests:

```bash
mvn clean test
```

The suite checks the algorithms against `Arrays.sort`, covers edge cases and
invalid QuickSelect input, and verifies bounded QuickSort recursion on a sorted
array of 100,000 elements.

## Run the benchmark

```bash
mvn -q compile exec:java
```

This runs every algorithm five times for each combination of:

- sizes: `1,000`, `10,000`, `100,000`, `1,000,000`;
- inputs: `random`, `sorted`, `duplicates` (values from 0 to 9).

The median-time run is written to `results.csv` with columns:

```text
algorithm,input,n,time_ms,comparisons,max_depth
```

The input generator uses a fixed seed so benchmark inputs are reproducible.

## Generate the plots

Create an isolated Python environment and install the plotting dependency:

```bash
python3 -m venv .venv
.venv/bin/python -m pip install -r requirements.txt
```

Generate all PNG charts:

```bash
.venv/bin/python scripts/plot_results.py
```

The command creates:

- `plots/time_vs_n.png`;
- `plots/depth_vs_n.png`;
- `plots/ratio_vs_n.png`.

## Project structure

```text
src/main/java/Algorithms/            algorithm implementations
src/main/java/Algorithms/metrics/    measurement container
src/main/java/Algorithms/benchmark/  input, benchmark, and CSV components
src/test/java/                       JUnit 5 tests
scripts/                             plot generator
plots/                               generated PNG charts
results.csv                          benchmark measurements
REPORT.MD                            asymptotic analysis and discussion
```

Repository: https://github.com/yernururue/1assignment_daa
