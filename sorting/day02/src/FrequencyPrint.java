import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        HashMap<String, Integer> hm = new HashMap<>();
        String[] ss = s.split(" ");

        for (int i = 0; i < ss.length; i++)
        {
            if (hm.containsKey(ss[i]))
            {
                hm.put(ss[i],hm.get(ss[i]) + 1);
            }
            else
            {
                hm.put(ss[i],1);
            }

        }


        List<Map.Entry<String, Integer>> array = new ArrayList<>();
        array.addAll(hm.entrySet());

        array.sort(Comparator.comparing(Map.Entry::getValue));

        String res = "";

        for (int i = 0; i < array.size(); i++)
        {
            int value = array.get(i).getValue();
            while (value > 0)
            {
                res += array.get(i).getKey();
                res += " ";
                value--;
            }
        }

        return res;
    }

}
