
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (s.length() < totalLen) {
            return result;
        }

        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        // Try each possible alignment of word boundaries
        for (int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int count = 0;
            Map<String, Integer> seen = new HashMap<>();

            for (int right = offset; right + wordLen <= s.length();
                 right += wordLen) {

                String word = s.substring(right, right + wordLen);

                if (!required.containsKey(word)) {
                    seen.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                seen.put(word, seen.getOrDefault(word, 0) + 1);
                count++;

                while (seen.get(word) > required.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);

                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }

                if (count == wordCount) {
                    result.add(left);

                    String leftWord = s.substring(left, left + wordLen);
                    seen.put(leftWord, seen.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}
