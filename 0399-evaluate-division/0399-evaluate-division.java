import java.util.*;

class Solution {

    public double[] calcEquation(
            List<List<String>> equations,
            double[] values,
            List<List<String>> queries) {

        // Graph:
        // variable -> (neighbor, weight)
        Map<String, List<Edge>> graph = new HashMap<>();

        // Build graph
        for (int i = 0; i < equations.size(); i++) {

            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            double value = values[i];

            graph.putIfAbsent(a, new ArrayList<>());
            graph.putIfAbsent(b, new ArrayList<>());

            // a / b = value
            graph.get(a).add(new Edge(b, value));

            // b / a = 1 / value
            graph.get(b).add(new Edge(a, 1.0 / value));
        }

        double[] answer = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {

            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            // Undefined variable
            if (!graph.containsKey(start) ||
                !graph.containsKey(end)) {

                answer[i] = -1.0;
                continue;
            }

            if (start.equals(end)) {
                answer[i] = 1.0;
                continue;
            }

            Set<String> visited = new HashSet<>();

            answer[i] = dfs(graph, start, end, 1.0, visited);
        }

        return answer;
    }

    private double dfs(
            Map<String, List<Edge>> graph,
            String current,
            String target,
            double product,
            Set<String> visited) {

        if (current.equals(target)) {
            return product;
        }

        visited.add(current);

        for (Edge edge : graph.get(current)) {

            if (visited.contains(edge.node)) {
                continue;
            }

            double result = dfs(
                    graph,
                    edge.node,
                    target,
                    product * edge.weight,
                    visited
            );

            if (result != -1.0) {
                return result;
            }
        }

        return -1.0;
    }

    private class Edge {
        String node;
        double weight;

        Edge(String node, double weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}