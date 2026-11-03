
import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            String current = queue.poll();

            // Check whether the current string is valid
            if (isValid(current)) {
                result.add(current);
                found = true;
            }

            // Once valid strings are found at this level,
            // do not remove more parentheses
            if (found) {
                continue;
            }

            // Generate strings by removing one parenthesis
            for (int i = 0; i < current.length(); i++) {
                char ch = current.charAt(i);

                if (ch != '(' && ch != ')') {
                    continue;
                }

                String next = current.substring(0, i)
                            + current.substring(i + 1);

                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }

        return result;
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}
