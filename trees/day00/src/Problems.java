import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        Collections.sort(values);

        BinarySearchTree tree = new BinarySearchTree();
        recursion(tree, values);
        return tree;
    }

    private static void recursion(BinarySearchTree tree, List<Integer> values)
    {
        if (values.size() == 0)
        {
            return;
        }
        if (values.size() == 1)
        {
            tree.add(values.get(0));
        }
        else
        {
            tree.add(values.get(values.size()/2));
            recursion(tree,values.subList(0,values.size()/2));
            recursion(tree,values.subList(values.size()/2 + 1, values.size()));
        }
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
