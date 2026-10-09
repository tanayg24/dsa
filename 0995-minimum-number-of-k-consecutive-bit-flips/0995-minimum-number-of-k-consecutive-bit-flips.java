class Solution {
    public int minKBitFlips(int[] nums, int k) {
        int n = nums.length;
        int[] flips = new int[n];

        int activeFlips = 0;
        int result = 0;

        for (int i = 0; i < n; i++) {

            // Previous flip ends before this position
            if (i >= k) {
                activeFlips -= flips[i - k];
            }

            // Current value after active flips
            if ((nums[i] + activeFlips) % 2 == 0) {

                // A zero must be flipped
                if (i + k > n) {
                    return -1;
                }

                flips[i] = 1;
                activeFlips++;
                result++;
            }
        }

        return result;
    }
}