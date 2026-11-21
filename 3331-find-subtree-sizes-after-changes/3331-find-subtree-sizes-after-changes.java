import java.util.Arrays;

class Solution {
    private int[] head, next, to, ans, lastSeen;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;
        this.ans = new int[n];
        this.lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

        // Fast array-based adjacency list (no ArrayList overhead)
        head = new int[n];
        next = new int[n];
        to = new int[n];
        Arrays.fill(head, -1);

        for (int i = 1; i < n; i++) {
            to[i] = i;
            next[i] = head[parent[i]];
            head[parent[i]] = i;
        }

        dfs(0, -1);
        return ans;
    }

    private void dfs(int u, int p) {
        int c = s.charAt(u) - 'a';
        int prev = lastSeen[c];
        lastSeen[c] = u;
        ans[u] = 1;

        for (int e = head[u]; e != -1; e = next[e]) {
            dfs(to[e], u);
        }

        lastSeen[c] = prev;
        if (p != -1) {
            ans[prev != -1 ? prev : p] += ans[u];
        }
    }
}
