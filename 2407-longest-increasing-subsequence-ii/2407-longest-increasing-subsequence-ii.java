class Solution {
    private int[] tree;
    private int size;

    public int lengthOfLIS(int[] nums, int k) {
        size = 100001;
        tree = new int[4 * size];

        int ans = 0;

        for (int num : nums) {
            int left = Math.max(1, num - k);
            int right = num - 1;

            int best = query(1, 1, size, left, right);
            int current = best + 1;

            update(1, 1, size, num, current);
            ans = Math.max(ans, current);
        }

        return ans;
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l || l > r) {
            return 0;
        }

        if (l <= start && end <= r) {
            return tree[node];
        }

        int mid = start + (end - start) / 2;

        int leftMax = query(node * 2, start, mid, l, r);
        int rightMax = query(node * 2 + 1, mid + 1, end, l, r);

        return Math.max(leftMax, rightMax);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = Math.max(tree[node], value);
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, end, index, value);
        }

        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }
}