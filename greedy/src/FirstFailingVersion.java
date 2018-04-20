public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
            for (long i = 0; i <= n; i++) {
                if (!isBadVersion.isFailingVersion(i) && isBadVersion.isFailingVersion(i + 1))
                    return (i+1);
                if (isBadVersion.isFailingVersion(n-i) && !isBadVersion.isFailingVersion(n - (i + 1)))
                    return n - i;
            }
            return n;
    }
}

