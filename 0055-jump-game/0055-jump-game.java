class Solution {
    public boolean canJump(int[] nums) {
        int farthest = 0;

        for (int i = 0; i < nums.length; i++) {

            // Current index is unreachable
            if (i > farthest) {
                return false;
            }

            farthest = Math.max(farthest, i + nums[i]);

            // Last index is reachable
            if (farthest >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }
}