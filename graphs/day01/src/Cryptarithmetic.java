import java.util.*;

public class Cryptarithmetic {

    // Do not modify this function (though feel free to use it)
    public static boolean validSolution(String S1, String S2, String S3, Map<Character, Integer> assignments) {
        return (stringToInt(S1, assignments) + stringToInt(S2, assignments) == stringToInt(S3, assignments))
                && assignments.get(S1.charAt(0)) != 0
                && assignments.get(S2.charAt(0)) != 0
                && assignments.get(S3.charAt(0)) != 0;
    }


    private static Iterable<Integer> randomOrder() {
        List<Integer> l = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(l);
        return l;
    }

    private static int stringToInt(String s, Map<Character, Integer> assignments) {
        int i = 0;
        for (int j = 0; j < s.length(); j++) {
            i *= 10;
            i += assignments.get(s.charAt(j));
        }
        return i;
    }


    public static boolean AssignChars(Map<Character, Integer> assignments, ArrayList<Character> unassigned, String S1, String S2, String S3)
    {
        if (unassigned.isEmpty() && validSolution(S1, S2, S3, assignments))
        {
            return true;
        }
        if (unassigned.isEmpty())
        {
            return false;
        }

        for (int i: randomOrder())
        {
            char current = unassigned.get(unassigned.size() - 1);
            assignments.put(current, i);
            unassigned.remove(unassigned.indexOf(current));
            if(AssignChars(assignments, unassigned, S1, S2, S3))
            {
                return true;
            }
            unassigned.add(current);
            assignments.remove(current, i);
        }
        return false;
    }

    public static Map<Character, Integer> solvePuzzle(String S1, String S2, String S3) {
        // TODO
        Set<Character> unassignedset = new HashSet<>();
        String total = S1 + S2 + S3;
        for (int i = 0; i < total.length(); i++)
        {
            unassignedset.add(total.charAt(i));
        }
        ArrayList<Character> unassigned = new ArrayList<>(unassignedset);
        Map<Character, Integer> assignments = new HashMap<>();
        AssignChars(assignments, unassigned, S1, S2, S3);
        return assignments;
    }
}
