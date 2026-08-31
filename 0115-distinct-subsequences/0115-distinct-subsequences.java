class Solution {
    public int numDistinct(String s, String t) {

        int m = s.length();
        int n = t.length();

        // dp[j] = number of ways to form t[0...j-1]
        int[] dp = new int[n + 1];

        // Empty t can always be formed in one way
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {

            // Go backwards so dp[j-1] is still
            // from the previous row
            for (int j = n; j >= 1; j--) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}