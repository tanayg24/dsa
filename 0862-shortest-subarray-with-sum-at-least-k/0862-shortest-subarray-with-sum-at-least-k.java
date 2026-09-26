import java.util.*;

class Solution {
    public int shortestSubarray(int[] nums, int k) {

        int n = nums.length;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();

        int answer = n + 1;

        for (int i = 0; i <= n; i++) {

            // Check if we can form a valid subarray
            while (!deque.isEmpty()
                    && prefix[i] - prefix[deque.peekFirst()] >= k) {

                answer = Math.min(
                    answer,
                    i - deque.pollFirst()
                );
            }

            // Remove worse prefix sums
            while (!deque.isEmpty()
                    && prefix[i] <= prefix[deque.peekLast()]) {

                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return answer == n + 1 ? -1 : answer;
    }
}