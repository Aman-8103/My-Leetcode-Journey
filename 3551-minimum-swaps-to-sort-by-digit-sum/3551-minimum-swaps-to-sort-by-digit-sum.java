class Solution {
    public int minSwaps(int[] arr) {
        int n=arr.length;
        Integer[] a=new Integer[n];
        Map<Integer,Integer> map=new HashMap<>();

        for(int i=0;i<n;i++) a[i]=arr[i];//copy elements in arr 'a'.
        Arrays.sort(a, (x, y) ->sum(x) != sum(y) ? sum(x) - sum(y) : x - y); //sort based on sum.

        for(int i=0;i<n;i++) map.put(a[i],i); // map the sorted elemsnts with index no.

        boolean[] vis=new boolean[n]; // for counting cycles
        int cycles=0;
        for (int i = 0; i < n; i++) {
            if (vis[i]) continue;
            cycles++;
            for (int j = i; !vis[j]; j = map.get(arr[j])) vis[j] = true;
        }
        return n-cycles;
        
    }

    int sum(int x){
            int s = 0;
            while (x > 0) {
                s += x % 10;
                x /= 10;
            }
            return s;
        }
}