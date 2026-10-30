import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // dist[u][count] stores the minimum weight to reach node u with 'count' consecutive identical characters
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        // Priority Queue stores: {accumulated_weight, node, consecutive_count}
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        dist[0][1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] curr = pq.poll();
            long d = curr[0];
            int u = (int) curr[1];
            int count = (int) curr[2];

            if (d > dist[u][count]) continue;
            if (u == n - 1) return (int) d;

            for (int[] edge : adj[u]) {
                int v = edge[0];
                long wt = edge[1];

                int nextCount = (labels.charAt(v) == labels.charAt(u)) ? count + 1 : 1;

                if (nextCount <= k) {
                    if (d + wt < dist[v][nextCount]) {
                        dist[v][nextCount] = d + wt;
                        pq.offer(new long[]{dist[v][nextCount], v, nextCount});
                    }
                }
            }
        }

        long minWeight = Long.MAX_VALUE;
        for (int c = 1; c <= k; c++) {
            minWeight = Math.min(minWeight, dist[n - 1][c]);
        }

        return minWeight == Long.MAX_VALUE ? -1 : (int) minWeight;
    }
}
