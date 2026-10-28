
import java.util.*;

class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] graph = new ArrayList[n];
        int[] indegree = new int[n];
        int[] finishTime = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            finishTime[i] = time[i];
        }

        // Build the graph
        for (int[] relation : relations) {
            int prev = relation[0] - 1;
            int next = relation[1] - 1;

            graph[prev].add(next);
            indegree[next]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Courses with no prerequisites
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int answer = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();

            answer = Math.max(answer, finishTime[course]);

            for (int next : graph[course]) {
                finishTime[next] = Math.max(
                    finishTime[next],
                    finishTime[course] + time[next]
                );

                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return answer;
    }
}
