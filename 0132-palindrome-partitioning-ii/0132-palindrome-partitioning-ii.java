class Solution {
    public int minCut(String s) {
        int n = s.length();

        // pal[i][j] = true if s[i...j] is a palindrome
        boolean[][] pal = new boolean[n][n];

        // dp[i] = minimum cuts needed for s[0...i]
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = i; // Maximum possible cuts
        }

        for (int end = 0; end < n; end++) {

            for (int start = 0; start <= end; start++) {

                if (s.charAt(start) == s.charAt(end) &&
                    (end - start <= 2 || pal[start + 1][end - 1])) {

                    pal[start][end] = true;

                    // Whole string s[0...end] is palindrome
                    if (start == 0) {
                        dp[end] = 0;
                    } else {
                        dp[end] = Math.min(dp[end], dp[start - 1] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }
}