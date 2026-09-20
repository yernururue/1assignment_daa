package Algorithms;

public class QuickSort {
    public static void algorithm(int[] nums) {
        quicksort(nums, 0, nums.length-1);
    }

    public static void quicksort(int[] nums, int start, int end) {
        if (end<=start) {
            return; //base case
        }
        int pivot = partition(nums, start, end);
        quicksort(nums, start, pivot - 1); //sort numbers smaller than the pivot
        quicksort(nums, pivot + 1, end); //sort numbers greater than the pivot
    }

    public static int partition(int[] nums, int start, int end) {
        int pivot = nums[end];
        int i = start - 1;

        for (int j = start; j<= end-1; j++){
            if (nums[j] < pivot) {
                i++;
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        i++;
        int temp = nums[i];
        nums[i] = nums[end];
        nums[end] = temp;

        return i;
    }
}
