
import java.util.*;

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> roots = new HashSet<>(dictionary);
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            String root = word;

            for (int i = 1; i <= word.length(); i++) {
                String prefix = word.substring(0, i);

                if (roots.contains(prefix)) {
                    root = prefix;
                    break;
                }
            }

            if (result.length() > 0) {
                result.append(" ");
            }

            result.append(root);
        }

        return result.toString();
    }
}
