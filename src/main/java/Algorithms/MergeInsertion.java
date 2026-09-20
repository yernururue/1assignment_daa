package Algorithms;

import Algorithms.metrics.Metrics;

public class MergeInsertion {
    public static void algorithm(int[] nums) {
        algorithm(nums, new Metrics());
    }

    public static void algorithm(int[] nums, Metrics metrics) {
        metrics.reset();
        long startTime = System.nanoTime();
        int[] buffer = new int[nums.length];
        sort(nums, buffer, 0, nums.length, metrics, 1);
        metrics.setTimeNanos(System.nanoTime() - startTime);
    }

    private static void sort(int[] nums, int[] buffer, int start, int end,
                             Metrics metrics, int depth) {
        metrics.updateMaxDepth(depth);
        if (end - start <= 15) {
            insertionSort(nums, start, end, metrics);
            return;
        }

        int middle = start + (end - start) / 2;

        sort(nums, buffer, start, middle, metrics, depth + 1);
        sort(nums, buffer, middle, end, metrics, depth + 1);
        merge(nums, buffer, start, middle, end, metrics);
    }

    private static void insertionSort(int[] nums, int start, int end, Metrics metrics) {
        for (int i = start + 1; i < end; i++) {
            for (int j = i; j > start; j--) {
                metrics.incrementComparisons();
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    private static void merge(int[] nums, int[] buffer, int start, int middle,
                              int end, Metrics metrics) {
        int left = start;
        int right = middle;
        int next = start;

        while (left < middle && right < end) {
            metrics.incrementComparisons();
            if (nums[left] <= nums[right]) {
                buffer[next++] = nums[left++];
            } else {
                buffer[next++] = nums[right++];
            }
        }

        while (left < middle) {
            buffer[next++] = nums[left++];
        }

        while (right < end) {
            buffer[next++] = nums[right++];
        }

        for (int i = start; i < end; i++) {
            nums[i] = buffer[i];
        }
    }
}
