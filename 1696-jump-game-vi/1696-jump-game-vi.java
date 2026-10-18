
import java.util.*;

class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];

        Deque<Integer> deque = new ArrayDeque<>();
        dp[0] = nums[0];
        deque.offerLast(0);

        for (int i = 1; i < n; i++) {
            // Remove indices outside the jump range
            while (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // Best score to reach index i
            dp[i] = nums[i] + dp[deque.peekFirst()];

            // Maintain decreasing DP values in deque
            while (!deque.isEmpty() &&
                   dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return dp[n - 1];
    }
}
