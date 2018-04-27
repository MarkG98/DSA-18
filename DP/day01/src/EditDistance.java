public class EditDistance {

    public static int minEditDist(String a, String b) {
        int aEnd = a.length();
        int bEnd = b.length();

        int[][] DP = new int[aEnd + 1][bEnd + 1];

        for (int i = 0; i <= aEnd ; i++) {
            for (int j = 0; j <= bEnd; j++) {

                if (i == 0) {
                    DP[i][j] = j;
                }

                else if (j == 0) {
                    DP[i][j] = i;
                }

                else if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    DP[i][j] = DP[i - 1][j - 1];
                }

                else {
                    DP[i][j] = 1 + Math.min(Math.min(DP[i][j - 1], DP[i - 1][j]), DP[i - 1][j - 1]);
                }
            }
        }
        return DP[aEnd][bEnd];
    }


}
