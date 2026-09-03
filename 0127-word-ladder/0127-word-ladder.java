import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> set = new HashSet<>(wordList);

        // endWord dictionary mein nahi hai
        if (!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            // Current level ke saare words process karo
            for (int k = 0; k < size; k++) {

                String word = queue.poll();

                if (word.equals(endWord)) {
                    return level;
                }

                char[] chars = word.toCharArray();

                // Har character ko change karke neighbors generate karo
                for (int i = 0; i < chars.length; i++) {

                    char original = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == original) {
                            continue;
                        }

                        chars[i] = c;

                        String nextWord = new String(chars);

                        if (set.contains(nextWord)) {

                            queue.offer(nextWord);

                            // Mark visited
                            set.remove(nextWord);
                        }
                    }

                    chars[i] = original;
                }
            }

            level++;
        }

        return 0;
    }
}