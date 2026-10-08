class Solution {
    public int minPatches(int[] nums, int n) {
        long reach = 0;
        int i = 0;
        int patches = 0;

        while (reach < n) {

            if (i < nums.length && nums[i] <= reach + 1) {
                // Existing number can extend our range
                reach += nums[i];
                i++;
            } else {
                // Patch the missing smallest number
                reach += reach + 1;
                patches++;
            }
        }

        return patches;
    }
}