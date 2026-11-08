import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] s, int[] t) {
        int n = s.length, m = t.length;
        int[] ans = new int[m];

        PriorityQueue<int[]> h1 = new PriorityQueue<>((a, b) -> 
            a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1])
        );

        PriorityQueue<int[]> h2 = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        for (int i = 0; i < n; i++) {
            h1.offer(new int[]{s[i], i});
        }

        int time = 0;
        for (int i = 0; i < m; i++) {
            time = Math.max(time, i);

            if (h1.isEmpty()) {
                time = Math.max(time, h2.peek()[0]);
            }

            while (!h2.isEmpty() && h2.peek()[0] <= time) {
                int[] curr = h2.poll();
                h1.offer(new int[]{curr[1], curr[2]});
            }

            int[] best = h1.poll();
            ans[i] = best[1];
            h2.offer(new int[]{time + t[i], best[0], best[1]});
        }

        return ans;
    }
}
