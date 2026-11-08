import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] s, int[] t) {
        int n = s.length, m = t.length;
        int[] ans = new int[m];

        PriorityQueue<Long> h1 = new PriorityQueue<>((a, b) -> {
            int wA = (int) (a >> 32), wB = (int) (b >> 32);
            if (wA != wB) return Integer.compare(wA, wB);
            return Integer.compare(a.intValue(), b.intValue());
        });

        PriorityQueue<long[]> h2 = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            int wA = (int) (a[1] >> 32), wB = (int) (b[1] >> 32);
            if (wA != wB) return Integer.compare(wA, wB);
            return Integer.compare((int) a[1], (int) b[1]);
        });

        for (int i = 0; i < n; i++) {
            h1.offer(((long) s[i] << 32) | i);
        }

        long time = 0;
        for (int i = 0; i < m; i++) {
            if (time < i) time = i;

            if (h1.isEmpty() && h2.peek()[0] > time) {
                time = h2.peek()[0];
            }

            while (!h2.isEmpty() && h2.peek()[0] <= time) {
                h1.offer(h2.poll()[1]);
            }

            long best = h1.poll();
            ans[i] = (int) best;
            h2.offer(new long[]{time + t[i], best});
        }

        return ans;
    }
}
