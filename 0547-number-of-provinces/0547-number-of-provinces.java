class Solution {

    public int findCircleNum(int[][] isConnected) {

        int n = isConnected.length;
        boolean[] visited = new boolean[n];

        int provinces = 0;

        for (int city = 0; city < n; city++) {

            if (!visited[city]) {

                // New province found
                provinces++;

                dfs(isConnected, visited, city);
            }
        }

        return provinces;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int city) {

        visited[city] = true;

        for (int nextCity = 0; nextCity < isConnected.length; nextCity++) {

            if (isConnected[city][nextCity] == 1 &&
                !visited[nextCity]) {

                dfs(isConnected, visited, nextCity);
            }
        }
    }
}