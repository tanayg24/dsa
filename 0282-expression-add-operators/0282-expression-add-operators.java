
import java.util.*;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(num, target, 0, 0, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(
        String num,
        int target,
        int index,
        long value,
        long lastOperand,
        StringBuilder expression,
        List<String> result
    ) {
        if (index == num.length()) {
            if (value == target) {
                result.add(expression.toString());
            }
            return;
        }

        for (int end = index; end < num.length(); end++) {
            // Numbers cannot have leading zeros
            if (end > index && num.charAt(index) == '0') {
                break;
            }

            String part = num.substring(index, end + 1);
            long current = Long.parseLong(part);

            int oldLength = expression.length();

            if (index == 0) {
                // First number: no operator before it
                expression.append(part);

                backtrack(
                    num, target, end + 1,
                    current, current, expression, result
                );

                expression.setLength(oldLength);
            } else {
                // Addition
                expression.append("+").append(part);

                backtrack(
                    num, target, end + 1,
                    value + current, current, expression, result
                );

                expression.setLength(oldLength);

                // Subtraction
                expression.append("-").append(part);

                backtrack(
                    num, target, end + 1,
                    value - current, -current, expression, result
                );

                expression.setLength(oldLength);

                // Multiplication: correct operator precedence
                expression.append("*").append(part);

                backtrack(
                    num, target, end + 1,
                    value - lastOperand + lastOperand * current,
                    lastOperand * current,
                    expression, result
                );

                expression.setLength(oldLength);
            }
        }
    }
}
