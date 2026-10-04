import java.util.*;

class Solution {

    public int[] loudAndRich(int[][] richer, int[] quiet) {

        int n = quiet.length;

        // graph[x] = people richer than x
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : richer) {
            int richerPerson = edge[0];
            int poorerPerson = edge[1];

            graph[poorerPerson].add(richerPerson);
        }

        // answer[i] = quietest person among
        // everyone known to be richer than or equal to i
        int[] answer = new int[n];

        Arrays.fill(answer, -1);

        for (int i = 0; i < n; i++) {
            dfs(i, graph, quiet, answer);
        }

        return answer;
    }

    private int dfs(
            int person,
            List<Integer>[] graph,
            int[] quiet,
            int[] answer) {

        // Already calculated
        if (answer[person] != -1) {
            return answer[person];
        }

        // Initially, person himself is a candidate
        answer[person] = person;

        // Check all richer people
        for (int richerPerson : graph[person]) {

            int candidate = dfs(
                    richerPerson,
                    graph,
                    quiet,
                    answer
            );

            // Candidate is quieter
            if (quiet[candidate] < quiet[answer[person]]) {
                answer[person] = candidate;
            }
        }

        return answer[person];
    }
}