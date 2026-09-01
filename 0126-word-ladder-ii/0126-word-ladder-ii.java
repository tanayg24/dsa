import java.util.*;

class Solution {

    public List<List<String>> findLadders(
            String beginWord,
            String endWord,
            List<String> wordList) {

        List<List<String>> result = new ArrayList<>();

        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) {
            return result;
        }

        /*
         * parent[word] = all previous words from which
         * word can be reached using a shortest path.
         */
        Map<String, List<String>> parent = new HashMap<>();

        for (String word : dict) {
            parent.put(word, new ArrayList<>());
        }

        parent.put(beginWord, new ArrayList<>());

        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);

        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {

            /*
             * Remove current-level words from dictionary.
             * This prevents going back to older levels.
             */
            for (String word : currentLevel) {
                dict.remove(word);
            }

            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {

                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {

                    char original = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {

                        if (c == original) {
                            continue;
                        }

                        chars[i] = c;

                        String next = new String(chars);

                        if (!dict.contains(next)) {
                            continue;
                        }

                        /*
                         * next is exactly one BFS level
                         * after word.
                         */
                        nextLevel.add(next);

                        parent.get(next).add(word);

                        if (next.equals(endWord)) {
                            found = true;
                        }
                    }

                    chars[i] = original;
                }
            }

            currentLevel = nextLevel;
        }

        if (!found) {
            return result;
        }

        /*
         * Build all shortest paths backwards:
         *
         * endWord -> ... -> beginWord
         */
        List<String> path = new ArrayList<>();
        path.add(endWord);

        buildPaths(
                endWord,
                beginWord,
                parent,
                path,
                result
        );

        return result;
    }

    private void buildPaths(
            String word,
            String beginWord,
            Map<String, List<String>> parent,
            List<String> path,
            List<List<String>> result) {

        if (word.equals(beginWord)) {

            List<String> sequence = new ArrayList<>(path);

            Collections.reverse(sequence);

            result.add(sequence);

            return;
        }

        for (String prev : parent.get(word)) {

            path.add(prev);

            buildPaths(
                    prev,
                    beginWord,
                    parent,
                    path,
                    result
            );

            path.remove(path.size() - 1);
        }
    }
}