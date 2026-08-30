class Solution {
    public int mySqrt(int n) {
        int low=0,high=n;
        int ans=1;
        while(low<=high){
            int mid=(low+high)/2;
            if((long)mid*mid <= n){
                ans=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;

        
    }
}