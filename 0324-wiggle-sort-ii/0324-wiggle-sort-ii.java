import java.util.*;

class Solution {
    public void wiggleSort(int[] nums) {

        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int small = (n + 1) / 2 - 1;
        int large = n - 1;

        for (int i = 0; i < n; i++) {

            if (i % 2 == 0) {
                nums[i] = sorted[small--];
            } else {
                nums[i] = sorted[large--];
            }
        }
    }
}