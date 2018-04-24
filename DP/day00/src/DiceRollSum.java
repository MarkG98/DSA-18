public class DiceRollSum {

    // Runtime: O(6n) --> O(n)
    // Space: O(n)
    public static int diceRollSum(int N) {
        int[] DP = new int[N + 1];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1;
        }
        return KMEANS(N, DP);
    }

    private static int KMEANS(int N, int[] DP) {
        // base cases
        if (N == 1 || N == 0) {
            return 1;
        }
        if (N < 0) {
            return 0;
        }

        // check if already seen
        if (DP[N] != -1) {
            return DP[N];
        }

        // try all guesses
        int total = 0;
        for (int i = 1; i <= 6; i++) {
            total += KMEANS(N - i, DP);
        }

        // store and return result
        DP[N] = total;
        return total;
    }



}
