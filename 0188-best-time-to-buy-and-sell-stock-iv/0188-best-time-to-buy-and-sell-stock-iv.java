
class Solution {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        // If k is large enough, unlimited transactions are possible
        if (k >= n / 2) {
            int profit = 0;

            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    profit += prices[i] - prices[i - 1];
                }
            }

            return profit;
        }

        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        // Initialize buy states
        for (int t = 1; t <= k; t++) {
            buy[t] = Integer.MIN_VALUE;
        }

        for (int price : prices) {
            for (int t = 1; t <= k; t++) {

                // Maximum profit after buying
                buy[t] = Math.max(
                    buy[t],
                    sell[t - 1] - price
                );

                // Maximum profit after selling
                sell[t] = Math.max(
                    sell[t],
                    buy[t] + price
                );
            }
        }

        return sell[k];
    }
}
