import java.util.Arrays;


public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        Arrays.sort(arr);
        int total = 0;


        for (int i = 0; i < arr.length - 2; i++)
        {
            int fd = i + 1;
            int bk = arr.length - 1;

            while (fd < bk)
            {
                int curr_sum = arr[i] + arr[fd] + arr[bk];
                if (curr_sum == sum)
                {
                    total++;
                    fd++;
                    bk--;
                }
                else if (curr_sum > sum)
                {
                    bk--;
                }
                else
                {
                    fd++;
                }
            }
        }
        return total;
    }
}
