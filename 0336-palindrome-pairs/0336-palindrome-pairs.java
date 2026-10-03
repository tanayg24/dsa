import java.util.*;

class Solution {

    public List<List<Integer>> palindromePairs(String[] words) {

        List<List<Integer>> result = new ArrayList<>();

        // word -> index
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            int len = word.length();

            for (int cut = 0; cut <= len; cut++) {

                String left = word.substring(0, cut);
                String right = word.substring(cut);

                // Case 1:
                // left is palindrome
                // reverse(right) + word forms palindrome
                if (isPalindrome(left)) {

                    String reversedRight = reverse(right);

                    if (map.containsKey(reversedRight)) {

                        int j = map.get(reversedRight);

                        if (i != j) {
                            result.add(Arrays.asList(j, i));
                        }
                    }
                }

                // Case 2:
                // right is palindrome
                // word + reverse(left) forms palindrome
                //
                // cut != len avoids duplicate when right = ""
                if (cut != len && isPalindrome(right)) {

                    String reversedLeft = reverse(left);

                    if (map.containsKey(reversedLeft)) {

                        int j = map.get(reversedLeft);

                        if (i != j) {
                            result.add(Arrays.asList(i, j));
                        }
                    }
                }
            }
        }

        return result;
    }

    private boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    private String reverse(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}