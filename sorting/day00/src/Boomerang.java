import java.util.HashMap;
import java.util.Collection;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        HashMap<Double,Integer> hm = new HashMap<>();
        int count;
        int total = 0;


        for (int j = 0; j < points.length; j++)
        {
            for (int i = 0; i < points.length; i++)
            {
                if (i != j)
                {
                    double dist = Math.sqrt((points[j][0]-points[i][0])*(points[j][0] - points[i][0]) + (points[j][1] - points[i][1])*(points[j][1]-points[i][1]));
                    if (hm.containsKey(dist))
                    {
                        count = hm.get(dist);
                        hm.put(dist,count + 1);
                    }
                    else
                    {
                        hm.put(dist,1);
                    }
                }

            }
            Collection <Integer> list = hm.values();
            for (int val: list)
            {
                total += val * (val - 1);
            }
            hm.clear();
        }

        return total;

    }
}

