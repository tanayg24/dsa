
class Solution {
    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;

        while (k > 0) {
            long steps = countSteps(n, curr, curr + 1);

            if (steps <= k) {
                curr++;
                k -= (int) steps;
            } else {
                curr *= 10;
                k--;
            }
        }

        return curr;
    }

    private long countSteps(long n, long first, long next) {
        long steps = 0;

        while (first <= n) {
            steps += Math.min(n + 1, next) - first;
            first *= 10;
            next *= 10;
        }

        return steps;
    }
}
