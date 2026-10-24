import java.util.*;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int MOD = 1_000_000_007;

        Stack<Integer> stack = new Stack<>();
        long sum = 0;

        for (int i = 0; i <= n; i++) {
            int curr = (i == n) ? 0 : arr[i];

            while (!stack.isEmpty() && arr[stack.peek()] > curr) {
                int mid = stack.pop();

                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;

                long leftCount = mid - left;
                long rightCount = right - mid;

                sum = (sum + (long) arr[mid] * leftCount % MOD
                           * rightCount % MOD) % MOD;
            }

            stack.push(i);
        }

        return (int) sum;
    }
}