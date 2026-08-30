class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low=1;
        int high=0;
        int ans=-1;
        for(int i:piles) high=Math.max(i,high);

        while(low<=high){
            int mid=(low+high)/2;
            if(counthrs(piles,mid) <= h){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        
        return ans;
        
    }
    long counthrs(int[] piles,int hourly){
        long totalhrs=0;
        for(int i=0;i<piles.length;i++){
            totalhrs+=Math.ceil((double)piles[i]/(double)hourly);
        }
        return totalhrs;
    }
}