package homework_mock;

import java.util.Arrays;

public class ReverseArray {

    public static void reverse(int[] nums) {
        if (nums == null || nums.length < 2) return;

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // Взаимный обмен значений (SWAP)
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Оригинал: " + Arrays.toString(arr));

        reverse(arr);
        System.out.println("Развернутый: " + Arrays.toString(arr));

        reverse(null); // Не должно упасть
    }
}
