class Solution {

    public String removeKdigits(String num, int k) {

        // If we remove all digits
        if (k == num.length()) {
            return "0";
        }

        StringBuilder stack = new StringBuilder();

        for (char digit : num.toCharArray()) {

            // Remove larger previous digits
            while (k > 0 &&
                   stack.length() > 0 &&
                   stack.charAt(stack.length() - 1) > digit) {

                stack.deleteCharAt(stack.length() - 1);
                k--;
            }

            stack.append(digit);
        }

        // If removals are still remaining,
        // remove from the end
        while (k > 0) {
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }

        // Remove leading zeroes
        int start = 0;

        while (start < stack.length() &&
               stack.charAt(start) == '0') {
            start++;
        }

        if (start == stack.length()) {
            return "0";
        }

        return stack.substring(start);
    }
}