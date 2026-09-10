class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // Increasing slope → peak is on the right
                left = mid + 1;
            } else {
                // Decreasing slope → peak is at mid or on the left
                right = mid;
            }
        }

        return left;
    }
}