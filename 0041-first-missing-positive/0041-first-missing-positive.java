class Solution {
    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        // Put every valid number x at index x - 1
        for (int i = 0; i < n; i++) {

            while (nums[i] >= 1 &&
                   nums[i] <= n &&
                   nums[nums[i] - 1] != nums[i]) {

                int correctIndex = nums[i] - 1;

                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            }
        }

        // Find the first position where the expected
        // positive number is missing
        for (int i = 0; i < n; i++) {

            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        // All 1...n are present
        return n + 1;
    }
}