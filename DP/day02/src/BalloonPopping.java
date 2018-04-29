public class BalloonPopping {

    public static int maxPoints(int[] B) {
        int DP[][] = new int[B.length][B.length];

        for (int subLen = 1; subLen <= B.length; subLen++) {
            for (int start = 0; start <= B.length - subLen; start++) {
                int end = start + (subLen - 1);

                for (int last = start; last <= end; last++) {
                    int leftValue = 1;
                    int rightValue = 1;

                    if (start != 0) {
                        leftValue = B[start - 1];
                    }
                    if (end != B.length - 1) {
                        rightValue = B[end + 1];
                    }

                    int beforeVal = 0;
                    int afterVal = 0;

                    if (start != last) {
                        beforeVal = DP[start][last - 1];
                    }
                    if (end != last) {
                        afterVal = DP[last + 1][end];
                    }

                    // find max of current value at DP[start][end] with the last "last" and the current "last"
                    DP[start][end] = Math.max(leftValue*B[last]*rightValue + beforeVal + afterVal, DP[start][end]);
                }
            }
        }
        return DP[0][B.length - 1];
    }
}
