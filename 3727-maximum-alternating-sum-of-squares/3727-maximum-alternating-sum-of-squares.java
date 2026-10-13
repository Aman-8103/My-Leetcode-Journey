class Solution {
    public long maxAlternatingSum(int[] arr) {
        int n=arr.length;
        for(int i=0;i<n;i++) arr[i]*=arr[i];
        Arrays.sort(arr);

        long ans=0;
        int mid=n/2;
        for(int i=0;i<mid;i++) ans-=arr[i];
        for(int i=mid;i<n;i++) ans+=arr[i];
        return ans;
        
    }
}