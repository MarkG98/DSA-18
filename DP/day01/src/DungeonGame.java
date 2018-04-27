public class DungeonGame {

    public static int minInitialHealth(int[][] map) {
        int r = map.length;
        int c = map[0].length;
        int DP[][] = new int[r][c];

        // initialize destination with one if cost is positive or min needed if negative
        if (map[r - 1][c - 1] > 0) {
            DP[r - 1][c - 1] = 1;
        }
        else {
            DP[r - 1][c - 1] = Math.abs(map[r - 1][c - 1]) + 1;
        }

        // Calculate last column by subtracting current map because if provided with +, don't need it for min.
        for (int i = r - 2; i >= 0 ; i--) {
            DP[i][c - 1] = Math.max(DP[i + 1][c - 1] - map[i][c - 1], 1);
        }

        // Same for last row
        for (int i = c - 2; i >= 0 ; i--) {
            DP[r - 1][i] = Math.max(DP[r - 1][i + 1] - map[r - 1][i], 1);
        }

        for (int i = r - 2; i >= 0 ; i--) {
            for (int j = c - 2; j >= 0; j--) {
                // Gets which cell (right or down) requires smaller amount of initial points
                int minPts = Math.min(DP[i + 1][j], DP[i][j + 1]);
                // Finds out if heading in the minPts direction would require certain amount of points because loosing a lot there
                // or if only one point is needed because of a large point gain there
                DP[i][j] = Math.max(minPts - map[i][j], 1);
            }
        }
        return DP[0][0];
    }
}
