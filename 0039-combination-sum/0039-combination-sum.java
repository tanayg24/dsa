import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(candidates, target, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(
        int[] candidates,
        int target,
        int start,
        List<Integer> current,
        List<List<Integer>> result
    ) {
        // Target reached
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {

            // Number is too large
            if (candidates[i] > target) {
                continue;
            }

            // Choose
            current.add(candidates[i]);

            // Same number can be reused
            backtrack(
                candidates,
                target - candidates[i],
                i,
                current,
                result
            );

            // Backtrack
            current.remove(current.size() - 1);
        }
    }
}