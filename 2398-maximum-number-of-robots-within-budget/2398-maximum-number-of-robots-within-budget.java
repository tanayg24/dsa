import java.util.*;

class Solution {
    public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
        int n = chargeTimes.length;
        Deque<Integer> deque = new ArrayDeque<>();

        long runningSum = 0;
        int left = 0;
        int maxRobots = 0;

        for (int right = 0; right < n; right++) {
            runningSum += runningCosts[right];

            // Keep chargeTimes in decreasing order
            while (!deque.isEmpty() &&
                   chargeTimes[deque.peekLast()] <= chargeTimes[right]) {
                deque.pollLast();
            }
            deque.offerLast(right);

            // Shrink window while cost exceeds budget
            while (left <= right &&
                   (long) chargeTimes[deque.peekFirst()]
                   + (right - left + 1L) * runningSum > budget) {

                if (deque.peekFirst() == left) {
                    deque.pollFirst();
                }

                runningSum -= runningCosts[left];
                left++;
            }

            maxRobots = Math.max(maxRobots, right - left + 1);
        }

        return maxRobots;
    }
}