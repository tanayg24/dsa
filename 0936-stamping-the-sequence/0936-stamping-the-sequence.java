
import java.util.*;

class Solution {
    public int[] movesToStamp(String stamp, String target) {
        char[] t = target.toCharArray();
        int n = t.length;
        int m = stamp.length();

        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[n - m + 1];

        int stars = 0;

        while (stars < n) {
            boolean changed = false;

            for (int i = 0; i <= n - m; i++) {
                if (visited[i]) continue;

                int replaced = 0;
                boolean match = true;

                for (int j = 0; j < m; j++) {
                    if (t[i + j] == '*') {
                        continue;
                    }

                    if (t[i + j] != stamp.charAt(j)) {
                        match = false;
                        break;
                    }

                    replaced++;
                }

                if (match && replaced > 0) {
                    visited[i] = true;
                    result.add(i);

                    for (int j = 0; j < m; j++) {
                        if (t[i + j] != '*') {
                            t[i + j] = '*';
                            stars++;
                        }
                    }

                    changed = true;
                }
            }

            if (!changed) {
                return new int[0];
            }
        }

        Collections.reverse(result);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
