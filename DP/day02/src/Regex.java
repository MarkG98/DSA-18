public class Regex {

    public static boolean isMatch(String s, String p) {
        int len1 = s.length();
        int len2 = p.length();

        boolean[][] DP = new boolean[len1 + 1][len2 + 1];
        DP[0][0] = true;

        for (int i = 1; i < len2; i++) {
            if (p.charAt(i) == '*' && DP[0][i - 1]) {
                DP[0][i + 1] = true;
            }
        }

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                // '.'.
                if (p.charAt(j) == '.') {
                    DP[i + 1][j + 1] = DP[i][j];
                }

                // char.
                if (p.charAt(j) == s.charAt(i)) {
                    DP[i + 1][j + 1] = DP[i][j];
                }

                if (p.charAt(j) == '*') {
                    // empty b/c proceding c doesn't match
                    if (p.charAt(j - 1) != '.' && p.charAt(j - 1) != s.charAt(i)) {
                        DP[i + 1][j + 1] = DP[i + 1][j - 1];
                    } else {
                        // check for one, multiple or empty matches
                        DP[i + 1][j + 1] = DP[i + 1][j] || DP[i][j + 1] || DP[i + 1][j - 1];
                    }
                }
            }
        }
        return DP[len1][len2];
    }

}
