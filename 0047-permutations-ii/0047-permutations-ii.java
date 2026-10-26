
import java.util.*;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];

        backtrack(nums, new ArrayList<>(), used, result);

        return result;
    }

    private void backtrack(int[] nums, List<Integer> current,
                           boolean[] used,
                           List<List<Integer>> result) {

        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // Skip duplicate choices at the same level
            if (i > 0 && nums[i] == nums[i - 1]
                    && !used[i - 1]) {
                continue;
            }

            current.add(nums[i]);
            used[i] = true;

            backtrack(nums, current, used, result);

            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}
