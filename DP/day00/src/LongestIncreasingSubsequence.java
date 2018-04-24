public class LongestIncreasingSubsequence {

    // Runtime: O(n^2)
    // Space: O(n)
    public static int LIS(int[] A) {
        // TODO
        if (A.length == 0) {
            return 0;
        }
        int[] DP = new int[A.length];
        DP[0] = 1;
        int answer = 1;
        for (int i = 1; i < DP.length; i++) {
            int maxVal = 0;
            for (int j = 0; j < i; j++) {
                if (A[i] > A[j]) {
                    maxVal = Math.max(maxVal, DP[j]);
                }
            }
            DP[i] = maxVal + 1;
            answer = Math.max(answer, DP[i]);
        }
        return answer;
    }
}
