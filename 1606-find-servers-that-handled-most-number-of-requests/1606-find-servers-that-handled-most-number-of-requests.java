import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arr, int[] ld) {
        int n = arr.length;
        int[] cnt = new int[k];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int[] t = new int[4 * k];
        build(t, 0, 0, k - 1);
        int mx = 0;
        for (int i = 0; i < n; i++) {
            int cur = arr[i];
            while (!pq.isEmpty() && pq.peek()[0] <= cur) {
                int[] s = pq.poll();
                upd(t, 0, 0, k - 1, s[1], 1);
            }
            int tgt = i % k;
            int s = find(t, 0, 0, k - 1, tgt, k - 1);
            if (s == -1 && tgt > 0) {
                s = find(t, 0, 0, k - 1, 0, tgt - 1);
            }
            if (s != -1) {
                cnt[s]++;
                mx = Math.max(mx, cnt[s]);
                upd(t, 0, 0, k - 1, s, 0);
                pq.offer(new int[]{cur + ld[i], s});
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (cnt[i] == mx) res.add(i);
        }
        return res;
    }

    private void build(int[] t, int n, int s, int e) {
        if (s == e) {
            t[n] = 1;
            return;
        }
        int m = s + (e - s) / 2;
        build(t, 2 * n + 1, s, m);
        build(t, 2 * n + 2, m + 1, e);
        t[n] = t[2 * n + 1] + t[2 * n + 2];
    }

    private void upd(int[] t, int n, int s, int e, int idx, int v) {
        if (s == e) {
            t[n] = v;
            return;
        }
        int m = s + (e - s) / 2;
        if (idx <= m) upd(t, 2 * n + 1, s, m, idx, v);
        else upd(t, 2 * n + 2, m + 1, e, idx, v);
        t[n] = t[2 * n + 1] + t[2 * n + 2];
    }

    private int find(int[] t, int n, int s, int e, int l, int r) {
        if (l > e || r < s || t[n] == 0) return -1;
        if (s == e) return s;
        int m = s + (e - s) / 2;
        int res = find(t, 2 * n + 1, s, m, l, r);
        if (res != -1) return res;
        return find(t, 2 * n + 2, m + 1, e, l, r);
    }
}
