package net.efgen.algorithms;


public class MaxFlow {
    private static final int inf = 1000000000;

    public static int[][] getMaxFlow(int n, int s, int t, int[][] c) {
        int[][] f = new int[n][n];
        int[] e = new int[n];
        int[] maxh = new int[n];
        int[] h = new int[n];
        h[s] = n - 1;
        for (int i = 0; i < n; ++i) {
            f[s][i] = c[s][i];
            f[i][s] = -f[s][i];
            e[i] = c[s][i];
        }


        int sz = 0;
        while (true) {
            if (sz == 0) {
                for (int i = 0; i < n; ++i)
                    if (i != s && i != t && e[i] > 0) {
                        if (sz > 0 && h[i] > h[maxh[0]])
                            sz = 0;
                        if (sz == 0 || h[i] == h[maxh[0]])
                            maxh[sz++] = i;
                    }
            }

            if (sz == 0) break;
            while (sz > 0) {
                int i = maxh[sz - 1];
                boolean pushed = false;

                for (int j = 0; j < n && e[i] > 0; ++j)
                    if (c[i][j] - f[i][j] > 0 && h[i] == h[j] + 1) {
                        pushed = true;
                        int flow = Math.min(c[i][j] - f[i][j], e[i]);
                        f[i][j] += flow;
                        f[j][i] -= flow;
                        e[i] -= flow;
                        e[j] += flow;
                        if (e[i] == 0) --sz;
                    }

                if (!pushed) {
                    h[i] = inf;
                    for (int j = 0; j < n; ++j) {
                        if (c[i][j] - f[i][j] > 0 && h[j] + 1 < h[i])
                            h[i] = h[j] + 1;
                    }

                    if (h[i] > h[maxh[0]]) {
                        sz = 0;
                        break;
                    }
                }
            }
        }

        return f;
    }
}
