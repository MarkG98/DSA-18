import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        ArrayList<Integer> ar = new ArrayList<>();
        for (int i = 0; i < A.length; i++)
        {
            ar.add(A[i]);
        }

        for (int j = 0; j < ar.size() - 1; j++)
        {
            if (ar.get(j) > ar.get(j + 1) && k > 0)
            {
                ar.remove(j);
                k--;
                if (j != 0)
                {
                    j--;
                    j--;
                }
            }
        }
        while (k > 0 && ar.size() > 0)
        {
            ar.remove(Collections.max(ar));
            k--;
        }


        // For now, return a List that's correct size, but contains only 0s
        LinkedList<Integer> l = new LinkedList<>();
        for (int i = 0; i < ar.size(); i++)
        {
            l.add(ar.get(i));
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {

        Node head = n;
        Node head_counter = n;
        Node count = n;
        Node second_list = n;
        int len = 0;

        if (head == null || head.next == null)
        {
            return true;
        }

        while (count.next != null)
        {
            len++;
            count = count.next;
        }
        len++;

        if (len % 2 == 0)
        {
            for (int i = 1; i <= (len / 2); i++)
            {
                head_counter = head_counter.next;
            }
            second_list = head_counter;
        }
        else
        {
            for (int i = 1; i <= (len + 1) / 2; i++)
            {
                head_counter = head_counter.next;
            }
            second_list = head_counter;
        }

        Node reversed_part = null;
        Node current = second_list;
        while (current != null) {
            Node next = current.next;
            current.next = reversed_part;
            reversed_part = current;
            current = next;
        }
        Node end = reversed_part;

        while (end != null)
        {
            if (end.val != head.val)
            {
                return false;
            }
            else
            {
                end = end.next;
                head = head.next;
            }
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        String result = "";
        Stack<Character> op = new Stack<>();

        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (c == ' ' || Character.isDigit(c))
            {
                result += c;
                result = result.trim();
            }
            else if (c == '+' || c == '-' || c == '/' || c == '*')
            {
                op.push(c);
            }
            else if (c == ')')
            {
                result += op.pop();
            }
        }
        return result.replaceAll(".(?=.)", "$0 ");
    }
}

