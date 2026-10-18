
import java.util.*;

class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers,
                             int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int left = 0;
        int right = Math.min(tasks.length, workers.length);

        while (left < right) {
            int mid = left + (right - left + 1) / 2;

            if (canAssign(tasks, workers, pills, strength, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private boolean canAssign(int[] tasks, int[] workers,
                              int pills, int strength, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int workerIndex = workers.length - 1;
        int pillsUsed = 0;

        // Try assigning the k easiest tasks among the hardest workers
        for (int i = k - 1; i >= 0; i--) {
            int task = tasks[i];

            // Add workers who can complete this task with a pill
            while (workerIndex >= workers.length - k &&
                   workerIndex >= 0 &&
                   (long) workers[workerIndex] + strength >= task) {
                deque.offerLast(workers[workerIndex]);
                workerIndex--;
            }

            if (deque.isEmpty()) {
                return false;
            }

            // Strongest available worker can do it without a pill
            if (deque.peekFirst() >= task) {
                deque.pollFirst();
            } else {
                // Otherwise, use a pill on the weakest eligible worker
                if (++pillsUsed > pills) {
                    return false;
                }
                deque.pollLast();
            }
        }

        return true;
    }
}
