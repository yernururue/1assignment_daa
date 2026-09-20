import csv
import math
from collections import defaultdict
from pathlib import Path

import matplotlib

matplotlib.use("Agg")
import matplotlib.pyplot as plt


RESULTS_PATH = Path("results.csv")
PLOTS_DIRECTORY = Path("plots")
COLORS = {
    "MergeInsertion": "#2563eb",
    "QuickSort": "#dc2626",
    "QuickSelect": "#16a34a",
}
LINE_STYLES = {
    "random": "-",
    "sorted": "--",
    "duplicates": ":",
}
MARKERS = {
    "random": "o",
    "sorted": "s",
    "duplicates": "^",
}


def read_results(path):
    grouped = defaultdict(list)
    with path.open(newline="", encoding="utf-8") as source:
        for row in csv.DictReader(source):
            key = (row["algorithm"], row["input"])
            grouped[key].append({
                "n": int(row["n"]),
                "time_ms": float(row["time_ms"]),
                "comparisons": int(row["comparisons"]),
                "max_depth": int(row["max_depth"]),
            })

    for values in grouped.values():
        values.sort(key=lambda value: value["n"])
    return grouped


def draw_plot(grouped, value_function, title, y_label, output_name,
              logarithmic_y=False):
    figure, axes = plt.subplots(figsize=(12, 7))

    for (algorithm, input_type), values in sorted(grouped.items()):
        axes.plot(
            [value["n"] for value in values],
            [value_function(algorithm, value) for value in values],
            color=COLORS[algorithm],
            linestyle=LINE_STYLES[input_type],
            marker=MARKERS[input_type],
            linewidth=2,
            markersize=6,
            label=f"{algorithm} / {input_type}",
        )

    axes.set_xscale("log")
    if logarithmic_y:
        axes.set_yscale("log")
    axes.set_title(title, fontsize=16, fontweight="bold")
    axes.set_xlabel("Input size n")
    axes.set_ylabel(y_label)
    axes.grid(True, which="both", alpha=0.25)
    axes.legend(loc="upper center", bbox_to_anchor=(0.5, -0.14),
                ncol=3, fontsize=9, frameon=False)
    figure.tight_layout()
    figure.savefig(PLOTS_DIRECTORY / output_name, dpi=180, bbox_inches="tight")
    plt.close(figure)


def comparison_ratio(algorithm, value):
    if algorithm == "QuickSelect":
        expected_growth = value["n"]
    else:
        expected_growth = value["n"] * math.log2(value["n"])
    return value["comparisons"] / expected_growth


def main():
    grouped = read_results(RESULTS_PATH)
    PLOTS_DIRECTORY.mkdir(parents=True, exist_ok=True)

    draw_plot(
        grouped,
        lambda _algorithm, value: value["time_ms"],
        "Execution time by input size",
        "Median time (ms)",
        "time_vs_n.png",
        logarithmic_y=True,
    )
    draw_plot(
        grouped,
        lambda _algorithm, value: value["max_depth"],
        "Maximum recursion depth by input size",
        "Maximum recursion depth",
        "depth_vs_n.png",
    )
    draw_plot(
        grouped,
        comparison_ratio,
        "Normalized comparison count",
        "comparisons / expected growth",
        "ratio_vs_n.png",
    )

    print(f"Wrote 3 plots to {PLOTS_DIRECTORY}")


if __name__ == "__main__":
    main()
