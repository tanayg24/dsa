import java.util.*;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            // First element
            row.add(1);

            // Middle elements
            for (int j = 1; j < i; j++) {
                int value = result.get(i - 1).get(j - 1)
                          + result.get(i - 1).get(j);

                row.add(value);
            }

            // Last element
            if (i > 0) {
                row.add(1);
            }

            result.add(row);
        }

        return result;
    }
}