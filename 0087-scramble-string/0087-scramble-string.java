
class Solution {
    private Boolean[][][] dp;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();

        if (n != s2.length()) {
            return false;
        }

        dp = new Boolean[n][n][n + 1];

        return solve(s1, s2, 0, 0, n);
    }

    private boolean solve(String s1, String s2,
                          int i, int j, int len) {

        // Already calculated
        if (dp[i][j][len] != null) {
            return dp[i][j][len];
        }

        String a = s1.substring(i, i + len);
        String b = s2.substring(j, j + len);

        // Same substring
        if (a.equals(b)) {
            return dp[i][j][len] = true;
        }

        // Check whether both substrings have same characters
        int[] count = new int[26];

        for (int k = 0; k < len; k++) {
            count[a.charAt(k) - 'a']++;
            count[b.charAt(k) - 'a']--;
        }

        for (int x : count) {
            if (x != 0) {
                return dp[i][j][len] = false;
            }
        }

        // Try every possible split
        for (int k = 1; k < len; k++) {

            // Case 1: No swap
            boolean noSwap =
                solve(s1, s2, i, j, k) &&
                solve(s1, s2, i + k, j + k, len - k);

            if (noSwap) {
                return dp[i][j][len] = true;
            }

            // Case 2: Swap
            boolean swap =
                solve(s1, s2, i, j + len - k, k) &&
                solve(s1, s2, i + k, j, len - k);

            if (swap) {
                return dp[i][j][len] = true;
            }
        }

        return dp[i][j][len] = false;
    }
}
