package divide_and_conquer;

import java.util.ArrayList;
import java.util.List;

public class RadioTowers {
    public static class Tower {
        public double x;
        public double y;
        public Tower(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    private static double getDist(Tower t1, Tower t2) {
        double xDiff = t1.x - t2.x;
        double yDiff = t1.y - t2.y;
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    private static boolean isWithin(Tower t1, Tower t2, int dist) {
        return getDist(t1, t2) <= dist;
    }

    // Strip contains a list of Towers sorted by x-coordinate, whose y-coordinates all fall in a 2-mile strip
    // Return true if no two towers are within 1 mile
    public static boolean checkStrip(List<Tower> strip) {
        // TODO
        for (int i = 0; i < strip.size(); i++)
        {
            for (int j = i + 1; j < i + 8; j++)
            {
                if (j < strip.size() && isWithin(strip.get(i),strip.get(j),1))
                {
                    return false;
                }
            }
        }
        return true;
    }

    // Return true if no two towers are within distance 1 of each other
    public static boolean validTowers(List<Tower> Lx, List<Tower> Ly) {
        assert Lx.size() == Ly.size();
        // TODO

        if (Lx.size() < 2)
        {
            return true;
        }
        if (Lx.size() == 2)
        {
            return !(isWithin(Lx.get(0),Lx.get(1),1));
        }

        List<Tower> LxBottom = new ArrayList<>();
        List<Tower> LxTop = new ArrayList<>();
        List<Tower> LyBottom = new ArrayList<>();
        List<Tower> LyTop = new ArrayList<>();

        double middle_y = Ly.get(Ly.size() / 2).y;

        for (int i = 0; i < Lx.size(); i++)
        {
            if (Ly.get(i).y > middle_y)
            {
                LyTop.add(Ly.get(i));
            }
            else
            {
                LyBottom.add(Ly.get(i));
            }
        }
        for (int j = 0; j < Ly.size(); j++)
        {
            if (Lx.get(j).y > middle_y)
            {
                LxTop.add(Lx.get(j));
            }
            else
            {
                LxBottom.add(Lx.get(j));
            }
        }

        if (!(validTowers(LxBottom, LyBottom)))
        {
            return false;
        }
        if (!(validTowers(LxTop, LyTop)))
        {
            return false;
        }

        List<Tower> ForStrip = new ArrayList<>();
        for (int k = 0; k < Lx.size(); k++)
        {
            if (Lx.get(k).y <= middle_y + 1 && Lx.get(k).y >= middle_y - 1)
            {
                ForStrip.add(Lx.get(k));
            }
        }

        return checkStrip(ForStrip);
    }
}
