
class Solution {
    public boolean isNumber(String s) {
        boolean digitSeen = false;
        boolean dotSeen = false;
        boolean exponentSeen = false;
        boolean digitAfterExponent = true;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch >= '0' && ch <= '9') {
                digitSeen = true;

                if (exponentSeen) {
                    digitAfterExponent = true;
                }
            } else if (ch == '+' || ch == '-') {
                if (i > 0 && s.charAt(i - 1) != 'e'
                          && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (ch == '.') {
                if (dotSeen || exponentSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (ch == 'e' || ch == 'E') {
                if (exponentSeen || !digitSeen) {
                    return false;
                }

                exponentSeen = true;
                digitAfterExponent = false;
            } else {
                return false;
            }
        }

        return digitSeen && digitAfterExponent;
    }
}
