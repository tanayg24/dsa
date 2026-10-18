
class Solution {
    public int findMaximumXOR(int[] nums) {
        int maxXor = 0;
        int mask = 0;

        for (int bit = 30; bit >= 0; bit--) {
            mask |= (1 << bit);

            java.util.Set<Integer> prefixes = new java.util.HashSet<>();

            for (int num : nums) {
                prefixes.add(num & mask);
            }

            int candidate = maxXor | (1 << bit);

            for (int prefix : prefixes) {
                if (prefixes.contains(candidate ^ prefix)) {
                    maxXor = candidate;
                    break;
                }
            }
        }

        return maxXor;
    }
}
