class Solution {
    public int maxDistance(int[] colors) {
        int n=colors.length;
        int max=1;

        for(int i=0;i<n;i++){
            int dist=0;
            for(int j=n-1;j>i;j--){
                if(colors[i]!=colors[j]){
                    dist=Math.abs(j-i);
                    break;
                }
            }
            if(dist>max) max=dist;
        }
        return max;
        
    }
}