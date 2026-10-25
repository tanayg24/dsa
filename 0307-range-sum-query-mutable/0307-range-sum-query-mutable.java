class NumArray {
    private int[] nums;
    private int[] bit;
    private int n;

    public NumArray(int[] nums) {
        this.n = nums.length;
        this.nums = nums.clone();
        this.bit = new int[n + 1];

        for (int i = 0; i < n; i++) {
            add(i + 1, nums[i]);
        }
    }

    private void add(int index, int delta) {
        while (index <= n) {
            bit[index] += delta;
            index += index & (-index);
        }
    }

    public void update(int index, int val) {
        int delta = val - nums[index];
        nums[index] = val;
        add(index + 1, delta);
    }

    private int prefixSum(int index) {
        int sum = 0;

        while (index > 0) {
            sum += bit[index];
            index -= index & (-index);
        }

        return sum;
    }

    public int sumRange(int left, int right) {
        return prefixSum(right + 1) - prefixSum(left);
    }
}