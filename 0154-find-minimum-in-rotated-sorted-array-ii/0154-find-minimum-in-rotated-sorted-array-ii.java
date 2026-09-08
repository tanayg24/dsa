class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                // Minimum is at mid or to the left
                right = mid;
            } 
            else if (nums[mid] > nums[right]) {
                // Minimum is to the right of mid
                left = mid + 1;
            } 
            else {
                // nums[mid] == nums[right]
                // We cannot determine which side has minimum
                right--;
            }
        }

        return nums[left];
    }
}
