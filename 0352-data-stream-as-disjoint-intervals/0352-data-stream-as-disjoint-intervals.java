import java.util.*;

class SummaryRanges {

    private TreeMap<Integer, Integer> map;

    public SummaryRanges() {
        map = new TreeMap<>();
    }

    public void addNum(int value) {

        // Interval whose start is <= value
        Integer left = map.floorKey(value);

        // Interval whose start is > value
        Integer right = map.higherKey(value);

        // Already covered by left interval
        if (left != null && map.get(left) >= value) {
            return;
        }

        // Check whether value connects left interval
        boolean mergeLeft = left != null && map.get(left) + 1 == value;

        // Check whether value connects right interval
        boolean mergeRight = right != null && value + 1 == right;

        if (mergeLeft && mergeRight) {

            // Merge left + value + right
            map.put(left, map.get(right));
            map.remove(right);

        } else if (mergeLeft) {

            // Extend left interval
            map.put(left, value);

        } else if (mergeRight) {

            // Extend right interval backwards
            int end = map.get(right);

            map.remove(right);
            map.put(value, end);

        } else {

            // New separate interval
            map.put(value, value);
        }
    }

    public int[][] getIntervals() {

        int[][] result = new int[map.size()][2];

        int index = 0;

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[index][0] = entry.getKey();
            result[index][1] = entry.getValue();
            index++;
        }

        return result;
    }
}