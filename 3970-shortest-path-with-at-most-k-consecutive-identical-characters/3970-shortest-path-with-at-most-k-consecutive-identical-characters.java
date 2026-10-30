import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        char[] lbl = labels.toCharArray();
        int m = edges.length;
        
        // 1. Easy & Efficient Primitive Adjacency List
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] to = new int[m];
        int[] weight = new int[m];
        int[] next = new int[m];
        
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int w = edges[i][2];
            to[i] = v;
            weight[i] = w;
            next[i] = head[u];
            head[u] = i;
        }

        // 2. Track standard dist[node][consecutive_count] matrix
        int[][] dist = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // 3. Simple standard Priority Queue storing straightforward 3-element arrays:
        // {accumulated_weight, node, consecutive_count}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1}); // Start weight 0, node 0, streak 1

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];
            int count = curr[2];

            if (d > dist[u][count]) continue;
            if (u == n - 1) return d; // Reached destination optimally

            // Traverse connected edges cleanly
            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                int w = weight[e];
                
                // Streak increases if labels match, resets to 1 if they change
                int nextCount = (lbl[v] == lbl[u]) ? count + 1 : 1;

                if (nextCount <= k && d + w < dist[v][nextCount]) {
                    dist[v][nextCount] = d + w;
                    pq.offer(new int[]{d + w, v, nextCount});
                }
            }
        }

        return -1;
    }
}
