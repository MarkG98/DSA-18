import java.util.HashMap;

public class SplitCoins {

    public static int splitCoins(int[] coins) {
        int l = coins.length;
        int sum = 0;
        for (int i = 0; i < l; i++) {
            sum += coins[i];
        }
        HashMap<Integer, Integer> DP = new HashMap<>();
        return recurse(coins, l, 0, sum, DP);

    }

    private static int recurse(int[] coins, int l, int tempSum, int sum, HashMap<Integer, Integer> DP) {

        if (l == 0) {
            DP.put(tempSum, Math.abs((sum - tempSum) - tempSum));
            return DP.get(tempSum);
        }

        if (DP.containsKey(tempSum)) {
            return DP.get(tempSum);
        }
        return Math.min(recurse(coins, l - 1, tempSum + coins[l - 1], sum, DP), recurse(coins, l - 1, tempSum, sum, DP));
    }
}
