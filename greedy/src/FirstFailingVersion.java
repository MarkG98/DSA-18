public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
            long start = 0;
            long end = n;

            while (true) {
                long thing = (start + end) / 2;

                if (isBadVersion.isFailingVersion(thing) && !isBadVersion.isFailingVersion(thing - 1)) {
                    return thing;
                }
                else if (!isBadVersion.isFailingVersion(thing)) {
                    start = thing + 1;
                }
                else if (isBadVersion.isFailingVersion(thing)) {
                    end = thing - 1;
                }
            }
    }
}

