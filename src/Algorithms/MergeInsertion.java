package Algorithms;

public class MergeInsertion {
    public static void algorithm(int[] nums) {
        int[] buffer = new int[nums.length];
        sort(nums, buffer, 0, nums.length);
    }

    private static void sort(int[] nums, int[] buffer, int start, int end) {
        if (end - start <= 15) {
            insertionSort(nums, start, end);
            return;
        }

        int middle = start + (end - start) / 2;

        sort(nums, buffer, start, middle); //left array
        sort(nums, buffer, middle, end); //right array
        merge(nums, buffer, start, middle, end);
    }

    private static void insertionSort(int[] nums, int start, int end) {
        for (int i = start + 1; i < end; i++) {
            for (int j = i; j > start; j--) {
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

    private static void merge(int[] nums, int[] buffer, int start, int middle, int end) {
        int left = start;
        int right = middle;
        int next = start;

        while (left < middle && right < end) {
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