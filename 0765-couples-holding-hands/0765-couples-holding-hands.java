
class Solution {
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        int[] position = new int[n];

        for (int i = 0; i < n; i++) {
            position[row[i]] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i += 2) {
            int first = row[i];

            // Find the partner of the first person
            int partner = first ^ 1;

            if (row[i + 1] != partner) {
                int partnerPos = position[partner];
                int current = row[i + 1];

                // Swap the current person with the partner
                row[partnerPos] = current;
                row[i + 1] = partner;

                // Update positions
                position[current] = partnerPos;
                position[partner] = i + 1;

                swaps++;
            }
        }

        return swaps;
    }
}
