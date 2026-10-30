
class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        // 1. Skip leading spaces
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 2. Determine sign
        int sign = 1;

        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') {
                sign = -1;
            }
            i++;
        }

        // 3. Read digits and handle overflow
        long num = 0;
        long INT_MAX = Integer.MAX_VALUE;
        long INT_MIN_ABS = -(long) Integer.MIN_VALUE;

        while (i < n) {
            char ch = s.charAt(i);

            if (ch < '0' || ch > '9') {
                break;
            }

            int digit = ch - '0';
            num = num * 10 + digit;

            if (sign == 1 && num > INT_MAX) {
                return Integer.MAX_VALUE;
            }

            if (sign == -1 && num > INT_MIN_ABS) {
                return Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (sign * num);
    }
}
