
import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    public int waysToBuildRooms(int[] prevRoom) {
        int n = prevRoom.length;

        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[prevRoom[i]].add(i);
        }

        // Precompute factorials and inverse factorials
        long[] fact = new long[n + 1];
        long[] invFact = new long[n + 1];

        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            fact[i] = fact[i - 1] * i % MOD;
        }

        invFact[n] = modPow(fact[n], MOD - 2);
        for (int i = n; i >= 1; i--) {
            invFact[i - 1] = invFact[i] * i % MOD;
        }

        // Iterative traversal avoids stack overflow for n = 100000
        List<Integer> order = new ArrayList<>();
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            order.add(node);

            for (int child : children[node]) {
                stack.push(child);
            }
        }

        int[] size = new int[n];
        long[] ways = new long[n];

        // Process children before their parents
        for (int i = order.size() - 1; i >= 0; i--) {
            int node = order.get(i);
            size[node] = 1;

            long result = 1;

            for (int child : children[node]) {
                size[node] += size[child];

                result = result * ways[child] % MOD;
                result = result * invFact[size[child]] % MOD;
            }

            ways[node] = result * fact[size[node] - 1] % MOD;
        }

        return (int) ways[0];
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % MOD;
            }

            base = base * base % MOD;
            exp >>= 1;
        }

        return result;
    }
}
