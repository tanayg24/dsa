
import java.util.*;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(
        String s,
        int index,
        int parts,
        List<String> current,
        List<String> result
    ) {
        if (parts == 4) {
            if (index == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {
            String segment = s.substring(index, index + len);

            // Leading zero is not allowed for multi-digit segments
            if (segment.length() > 1 && segment.charAt(0) == '0') {
                break;
            }

            // An IP segment cannot exceed 255
            if (Integer.parseInt(segment) > 255) {
                break;
            }

            current.add(segment);

            backtrack(s, index + len, parts + 1, current, result);

            current.remove(current.size() - 1);
        }
    }
}
