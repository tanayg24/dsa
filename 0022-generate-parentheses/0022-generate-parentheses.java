import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrack(n, 0, 0, "", result);

        return result;
    }

    private void backtrack(
        int n,
        int open,
        int close,
        String current,
        List<String> result
    ) {
        // Complete valid combination
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // Add '('
        if (open < n) {
            backtrack(
                n,
                open + 1,
                close,
                current + "(",
                result
            );
        }

        // Add ')' only when it won't make string invalid
        if (close < open) {
            backtrack(
                n,
                open,
                close + 1,
                current + ")",
                result
            );
        }
    }
}