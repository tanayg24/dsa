
import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // Build graph: connect emails belonging to the same account
        for (List<String> account : accounts) {
            String name = account.get(0);

            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);

                emailToName.put(email, name);
                graph.putIfAbsent(email, new HashSet<>());

                if (i > 1) {
                    String previousEmail = account.get(i - 1);
                    graph.get(email).add(previousEmail);
                    graph.get(previousEmail).add(email);
                }
            }
        }

        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        // DFS to collect connected emails
        for (String email : graph.keySet()) {
            if (visited.contains(email)) {
                continue;
            }

            List<String> mergedEmails = new ArrayList<>();
            dfs(email, graph, visited, mergedEmails);

            Collections.sort(mergedEmails);

            List<String> mergedAccount = new ArrayList<>();
            mergedAccount.add(emailToName.get(email));
            mergedAccount.addAll(mergedEmails);

            result.add(mergedAccount);
        }

        return result;
    }

    private void dfs(String email,
                     Map<String, Set<String>> graph,
                     Set<String> visited,
                     List<String> emails) {

        visited.add(email);
        emails.add(email);

        for (String neighbor : graph.get(email)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, emails);
            }
        }
    }
}
