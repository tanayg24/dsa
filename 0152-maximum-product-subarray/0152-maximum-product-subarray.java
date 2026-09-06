class Solution {
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int answer = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // Negative number swaps max and min
            if (num < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(num, maxProduct * num);
            minProduct = Math.min(num, minProduct * num);

            answer = Math.max(answer, maxProduct);
        }

        return answer;
    }
}