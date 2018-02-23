import java.util.Arrays;
import java.util.HashMap;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        int sum[] = new int[nums.length + 1];
        HashMap<Integer, Integer> hm = new HashMap<>();
        int[] result = {0,0};
        int max_diff = 0;


        for (int i = 1; i < sum.length; i++)
        {
            if (nums[i - 1] == 1) {
                sum[i] = sum[i - 1] + 1;
            } else {
                sum[i] = sum[i - 1] - 1;
            }
        }

        for (int j = 0; j < sum.length; j++)
        {
            if (hm.containsKey(sum[j]))
            {
                if(j - hm.get(sum[j]) > max_diff)
                {
                    result[0] = hm.get(sum[j]);
                    result[1] = j - 1;

                    max_diff = j - hm.get(sum[j]);
                }
            }
            else
            {
                hm.put(sum[j], j);
            }
        }
        System.out.println(Arrays.toString(sum));
        System.out.println(result[0]);
        System.out.println(result[1]);
        return result;
    }
}
