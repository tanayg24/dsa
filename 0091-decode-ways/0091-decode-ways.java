class Solution {
    public int numDecodings(String s) {

        int n = s.length();

        int[] dp = new int[n + 1];

        dp[0] = 1;

        // First character
        if (s.charAt(0) != '0') {
            dp[1] = 1;
        }

        for (int i = 2; i <= n; i++) {

            char current = s.charAt(i - 1);
            char previous = s.charAt(i - 2);

            // One digit: 1 to 9
            if (current != '0') {
                dp[i] += dp[i - 1];
            }

            // Two digits: 10 to 26
            int twoDigit =
                    (previous - '0') * 10
                    + (current - '0');

            if (twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i - 2];
            }
        }

        return dp[n];
    }
}