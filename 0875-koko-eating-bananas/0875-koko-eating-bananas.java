class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low=1;
        int high=0;
        int k=1;
        for(int i:piles) high=Math.max(i,high);

        while(low<=high){
            int mid=low+(high-low)/2;
            if(counthrs(piles,mid,h)){
                k=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        
        return k;
        
    }
    boolean counthrs(int[] piles,int hourly,int h){
        long totalhrs=0;

        for(int i=0;i<piles.length;i++){
            totalhrs+=(piles[i] + hourly - 1)/hourly;
            if(totalhrs > h) return false;
        }
        return true;
    }
}