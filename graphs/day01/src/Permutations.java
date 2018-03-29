import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO
        Set<Integer> unused = new HashSet<>();
        for (int u : A)
        {
            unused.add(u);
        }
        LinkedList<Integer> curr = new LinkedList<>();
        List<List<Integer>> permutations = new LinkedList<>();
        backtrack(curr,unused,permutations);
        return permutations;
    }

}
