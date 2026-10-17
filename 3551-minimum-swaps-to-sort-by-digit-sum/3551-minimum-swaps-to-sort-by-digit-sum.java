
import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] a = new int[n][2];

        for (int i = 0; i < n; i++) {
            a[i][0] = nums[i];
            a[i][1] = i;
        }

        Arrays.sort(a, (x, y) -> {
            int s1 = sum(x[0]), s2 = sum(y[0]);
            if (s1 != s2) return Integer.compare(s1, s2);
            return Integer.compare(x[0], y[0]);
        });

        boolean[] vis = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;

            int j = i, size = 0;

            while (!vis[j]) {
                vis[j] = true;
                j = a[j][1];
                size++;
            }
            if (size > 1) swaps += size - 1;
        }
        return swaps;
    }

    private int sum(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}
