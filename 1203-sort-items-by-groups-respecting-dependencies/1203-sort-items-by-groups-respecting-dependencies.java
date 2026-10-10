
import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group,
                           List<List<Integer>> beforeItems) {

        // Give every ungrouped item its own group
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = m++;
            }
        }

        List<List<Integer>> itemGraph = new ArrayList<>();
        List<List<Integer>> groupGraph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            groupGraph.add(new ArrayList<>());
        }

        int[] itemIndegree = new int[n];
        int[] groupIndegree = new int[m];

        // Build graphs
        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                if (group[prev] == group[i]) {
                    itemGraph.get(prev).add(i);
                    itemIndegree[i]++;
                } else {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        // Topologically sort items
        List<Integer> itemOrder =
            topoSort(itemGraph, itemIndegree);

        if (itemOrder.size() != n) {
            return new int[0];
        }

        // Topologically sort groups
        List<Integer> groupOrder =
            topoSort(groupGraph, groupIndegree);

        if (groupOrder.size() != m) {
            return new int[0];
        }

        // Collect items according to their group
        List<List<Integer>> groupedItems = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            groupedItems.add(new ArrayList<>());
        }

        for (int item : itemOrder) {
            groupedItems.get(group[item]).add(item);
        }

        // Output items group by group
        int[] answer = new int[n];
        int index = 0;

        for (int g : groupOrder) {
            for (int item : groupedItems.get(g)) {
                answer[index++] = item;
            }
        }

        return answer;
    }

    private List<Integer> topoSort(
            List<List<Integer>> graph, int[] indegree) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);

            for (int next : graph.get(node)) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        return order;
    }
}
