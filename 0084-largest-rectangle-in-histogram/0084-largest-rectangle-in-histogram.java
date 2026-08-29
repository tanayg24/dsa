import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i <= heights.length; i++) {

            // Sentinel height 0 at the end
            int currentHeight = (i == heights.length)
                    ? 0
                    : heights[i];

            while (!stack.isEmpty()
                    && heights[stack.peek()] > currentHeight) {

                int height = heights[stack.pop()];

                int right = i;

                int left = stack.isEmpty()
                        ? -1
                        : stack.peek();

                int width = right - left - 1;

                int area = height * width;

                maxArea = Math.max(maxArea, area);
            }

            stack.push(i);
        }

        return maxArea;
    }
}