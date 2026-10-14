
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int left = -1, right = -1;

        int maxSeen = Integer.MIN_VALUE;
        int minSeen = Integer.MAX_VALUE;

        // Find the right boundary
        for (int i = 0; i < n; i++) {
            maxSeen = Math.max(maxSeen, nums[i]);

            if (nums[i] < maxSeen) {
                right = i;
            }
        }

        // Find the left boundary
        for (int i = n - 1; i >= 0; i--) {
            minSeen = Math.min(minSeen, nums[i]);

            if (nums[i] > minSeen) {
                left = i;
            }
        }

        return right == -1 ? 0 : right - left + 1;
    }
}
