import java.util.*;

class Solution {

    public int findMaxValueOfEquation(int[][] points, int k) {

        Deque<Integer> deque = new ArrayDeque<>();

        int answer = Integer.MIN_VALUE;

        for (int j = 0; j < points.length; j++) {

            int xj = points[j][0];
            int yj = points[j][1];

            // Remove points that are too far away
            while (!deque.isEmpty() &&
                   xj - points[deque.peekFirst()][0] > k) {

                deque.pollFirst();
            }

            // Best previous point
            if (!deque.isEmpty()) {

                int i = deque.peekFirst();

                int xi = points[i][0];
                int yi = points[i][1];

                answer = Math.max(
                    answer,
                    yi - xi + yj + xj
                );
            }

            // Maintain decreasing order of (y - x)
            int currentValue = yj - xj;

            while (!deque.isEmpty()) {

                int last = deque.peekLast();

                int lastValue =
                    points[last][1] - points[last][0];

                if (lastValue <= currentValue) {
                    deque.pollLast();
                } else {
                    break;
                }
            }

            deque.offerLast(j);
        }

        return answer;
    }
}
