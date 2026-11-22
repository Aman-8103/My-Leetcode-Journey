class Solution {
    public int maxDistance(int[] colors) {
        int n=colors.length;
        int max=1;

        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                int dist=0;
                if(colors[i]!=colors[j]) dist=Math.abs(j-i);
                if(dist>max) max=dist;
            }
        }
        return max;
        
    }
}