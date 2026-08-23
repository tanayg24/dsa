class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Always binary search on the smaller array
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;

        int low = 0;
        int high = m;

        while (low <= high) {

            int i = (low + high) / 2;
            int j = (m + n + 1) / 2 - i;

            // Boundary values
            int left1 = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int left2 = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

            // Correct partition
            if (left1 <= right2 && left2 <= right1) {

                // Odd total length
                if ((m + n) % 2 == 1) {
                    return Math.max(left1, left2);
                }

                // Even total length
                double leftMax = Math.max(left1, left2);
                double rightMin = Math.min(right1, right2);

                return (leftMax + rightMin) / 2.0;
            }

            // nums1 partition is too far right
            else if (left1 > right2) {
                high = i - 1;
            }

            // nums1 partition is too far left
            else {
                low = i + 1;
            }
        }

        return 0.0;
    }
}