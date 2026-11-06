class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        
        int[][] q = new int[m][2];
        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }
        
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        Arrays.sort(q, (a, b) -> Integer.compare(a[0], b[0]));
        
        Map<Integer, Integer> map = new HashMap<>();
        int l = 0, r = 0;
        
        for (int i = 0; i < m; i++) {
            int t = q[i][0];
            int idx = q[i][1];
            
            while (r < logs.length && logs[r][1] <= t) {
                int id = logs[r][0];
                map.put(id, map.getOrDefault(id, 0) + 1);
                r++;
            }
            
            while (l < logs.length && logs[l][1] < t - x) {
                int id = logs[l][0];
                if (map.containsKey(id)) {
                    map.put(id, map.get(id) - 1);
                    if (map.get(id) == 0) {
                        map.remove(id);
                    }
                }
                l++;
            }
            
            ans[idx] = n - map.size();
        }
        
        return ans;
    }
}
