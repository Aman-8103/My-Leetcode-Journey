class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        long[] q = new long[m];
        for (int i = 0; i < m; i++) {
            q[i] = ((long) queries[i] << 32) | i;
        }
        
        // Sort logs by time (index 1) and queries chronologically
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(q);
        
        int[] freq = new int[n + 1]; 
        int active = 0; 
        int l = 0, r = 0;
        
        for (int i = 0; i < m; i++) {
            int t = (int) (q[i] >> 32);
            int idx = (int) q[i];
            
            while (r < logs.length && logs[r][1] <= t) {
                int id = logs[r][0];
                if (freq[id] == 0) active++;
                freq[id]++;
                r++;
            }
            
            while (l < logs.length && logs[l][1] < t - x) {
                int id = logs[l][0];
                freq[id]--;
                if (freq[id] == 0) active--;
                l++;
            }
            
            ans[idx] = n - active;
        }
        
        return ans;
    }
}
