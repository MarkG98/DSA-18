import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {


        int mid = ((nums.length) / 2);

        if (peakOneD(mid, nums) == 0)
        {
            return mid;
        }
        else if (peakOneD(mid, nums) == -1)
        {
            findOneDPeak(Arrays.copyOfRange(nums,0, mid));
        }
        else if (peakOneD(mid, nums) == 1)
        {
            findOneDPeak(Arrays.copyOfRange(nums,mid + 1, nums.length));
        }
        return nums[nums.length - 1];
    }

    public static int[] findTwoDPeak(int[][] nums) {
        int sol[] = new int[2];

        int right = nums[0].length;
        int left = 0;
        int lo = 0;
        int hi = nums.length;
        int c = 0;

        while(right >= left && hi >= lo)
        {
            if (c == 0)
            {
                int mid_col = (right + left) / 2;

                int global_max = maxYIndex(mid_col, lo, hi, nums);
                if (peakX(mid_col, global_max, nums) == -1)
                {
                    c++;
                    right = mid_col;
                }
                else if (peakX(mid_col, global_max, nums) == 1)
                {
                    c++;
                    left = mid_col + 1;
                }
                else if (peakX(mid_col, global_max, nums) == 0)
                {
                    sol[0] = global_max;
                    sol[1] = mid_col;
                    return sol;
                }
            }
            else
            {
                int mid_row = (hi + lo) / 2;

                int global = maxXIndex(mid_row, left, right, nums);
                if (peakY(global, mid_row, nums) == -1)
                {
                    c--;
                    hi = mid_row;
                }
                else if (peakY(global, mid_row, nums) == 1)
                {
                    c--;
                    lo = mid_row + 1;
                }
                else if (peakY(global, mid_row, nums) == 0)
                {
                    sol[0] = mid_row;
                    sol[1] = global;
                    return sol;
                }
            }
        }
        return null;
    }

}
