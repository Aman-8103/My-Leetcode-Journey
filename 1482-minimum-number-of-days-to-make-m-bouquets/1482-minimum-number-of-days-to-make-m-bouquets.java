class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        if((long)n < (long)(m*k)) return -1;

        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i:bloomDay){
            min=Math.min(i,min);
            max=Math.max(i,max);
        }
        
        int low=min,high=max;
        int ans=-1;
        while(low<=high){
            int mid=(low+high)/2;
            if(possible(bloomDay,mid,m,k)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;     
    }

    boolean possible(int[] arr,long day,int m,int k){
        int count=0;
        int n_bouq=0;

        for(int i=0;i<arr.length;i++){
            if(day >= arr[i]){
                count++;
            }else{
                n_bouq+=(count/k);
                count=0;
            }
        }
        n_bouq+=(count/k);

        return n_bouq >= m;
    }
}