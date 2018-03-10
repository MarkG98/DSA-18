public class Problems {

    public static int leastSum(int[] A) {
        //TODO
        int[] B = new int[10];

        for (int i = 0; i <  A.length; i++)
        {
            B[A[i]] += 1;
        }

        boolean flag = true;

        int first = 0;
        int second = 0;

        for (int j = 0; j < B.length; j++)
        {
            if (B[j] > 0)
            {
                while (B[j] > 0)
                {
                    if (flag)
                    {
                        first = first * 10 + j;
                    }
                    else
                    {
                        second = second * 10 + j;
                    }
                    flag = !(flag);
                    B[j]--;
                }
            }
        }
        return first + second;

    }
}
