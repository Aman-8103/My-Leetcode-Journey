import java.util.*;

class Solution {
    private int[] head, next, to;
    private int edgeIdx;
    private int[] in, out, depth, nodeMask;
    private int[][] up;
    private int timer, LOGN;
    private int[] bit;

    private void addEdge(int u, int v) {
        to[edgeIdx] = v; next[edgeIdx] = head[u]; head[u] = edgeIdx++;
    }

    private void dfs(int u, int p, int d, int currentMask) {
        in[u] = ++timer;
        depth[u] = d;
        up[u][0] = (p == -1) ? u : p;
        for (int i = 1; i < LOGN; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        updateBit(in[u], in[u], currentMask);

        for (int e = head[u]; e != -1; e = next[e]) {
            int v = to[e];
            if (v != p) {
                dfs(v, u, d + 1, currentMask ^ nodeMask[v]);
            }
        }
        out[u] = timer;
    }

    private void updateBit(int l, int r, int val) {
        for (int i = l; i < bit.length; i += i & -i) bit[i] ^= val;
        for (int i = r + 1; i < bit.length; i += i & -i) bit[i] ^= val;
    }

    private int queryBit(int idx) {
        int sum = 0;
        for (int i = idx; i > 0; i -= i & -i) sum ^= bit[i];
        return sum;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) { int t = u; u = v; v = t; }
        for (int i = LOGN - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) u = up[u][i];
        }
        if (u == v) return u;
        for (int i = LOGN - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) { u = up[u][i]; v = up[v][i]; }
        }
        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        LOGN = 32 - Integer.numberOfLeadingZeros(n);
        head = new int[n]; Arrays.fill(head, -1);
        next = new int[2 * n]; to = new int[2 * n]; edgeIdx = 0;
        
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]); 
            addEdge(edge[1], edge[0]);
        }

        in = new int[n]; out = new int[n]; depth = new int[n]; bit = new int[n + 2];
        up = new int[n][LOGN]; nodeMask = new int[n];
        
        for (int i = 0; i < n; i++) {
            nodeMask[i] = 1 << (s.charAt(i) - 'a');
        }

        timer = 0;
        dfs(0, -1, 0, nodeMask[0]);

        List<Boolean> results = new ArrayList<>();
        for (String q : queries) {
            if (q.charAt(0) == 'u') { // update u c
                int u = 0, idx = 7;
                while (idx < q.length() && q.charAt(idx) != ' ') {
                    u = u * 10 + (q.charAt(idx) - '0'); 
                    idx++;
                }
                char c = q.charAt(idx + 1);
                int newMask = 1 << (c - 'a');
                int delta = nodeMask[u] ^ newMask;
                nodeMask[u] = newMask;
                updateBit(in[u], out[u], delta);
            } else { // query u v
                int u = 0, v = 0, idx = 6;
                while (q.charAt(idx) != ' ') { 
                    u = u * 10 + (q.charAt(idx) - '0'); 
                    idx++; 
                }
                idx++;
                while (idx < q.length()) { 
                    v = v * 10 + (q.charAt(idx) - '0'); 
                    idx++; 
                }
                
                int lca = getLCA(u, v);
                int pathMask = queryBit(in[u]) ^ queryBit(in[v]) ^ nodeMask[lca];
                results.add((pathMask & (pathMask - 1)) == 0);
            }
        }
        return results;
    }
}
