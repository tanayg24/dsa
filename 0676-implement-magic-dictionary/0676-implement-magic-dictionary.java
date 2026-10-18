
class MagicDictionary {
    private String[] words;

    public MagicDictionary() {
    }

    public void buildDict(String[] dictionary) {
        words = dictionary;
    }

    public boolean search(String searchWord) {
        for (String word : words) {
            if (word.length() != searchWord.length()) {
                continue;
            }

            int differences = 0;

            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != searchWord.charAt(i)) {
                    differences++;

                    if (differences > 1) {
                        break;
                    }
                }
            }

            if (differences == 1) {
                return true;
            }
        }

        return false;
    }
}
