package net.efgen.algorithms;


public class Math {
    public static long gcd(long a, long b) {
        while (b > 0) {
            long t = a%b;
            a = b;
            b = t;
        }
        return a;
    }
    public static int gcd(int a, int b) {
        while (b > 0) {
            int t = a%b;
            a = b;
            b = t;
        }
        return a;
    }

    public static long modPow(long x, long pow, long mod) {
        long res = 1;
        while (pow > 0) {
            if ((pow&2) != 0) {
                res = res * x % mod;
            }
            x = x * x % mod;
            pow >>= 1;
        }
        return res;
    }

    public static long modInverse(long x, long p) {
        return modPow(x, p-2, p);
    }

    int[] gcdExtended(int a, int b) {
        if (a == 0) {
            return new int[]{b, 0, 1};
        }
        int[] d = gcdExtended(b%a, a);
        int x = d[2] - (b / a) * d[1];
        int y = d[1];
        d[1] = x;
        d[2] = y;
        return d;
    }
}
