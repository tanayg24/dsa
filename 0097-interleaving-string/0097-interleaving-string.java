
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        // Length must match
        if (m + n != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty strings
        dp[0][0] = true;

        // First column: only s1 is used
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] &&
                       s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // First row: only s2 is used
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] &&
                       s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                char current = s3.charAt(i + j - 1);

                boolean fromS1 =
                    dp[i - 1][j] &&
                    s1.charAt(i - 1) == current;

                boolean fromS2 =
                    dp[i][j - 1] &&
                    s2.charAt(j - 1) == current;

                dp[i][j] = fromS1 || fromS2;
            }
        }

        return dp[m][n];
    }
}
