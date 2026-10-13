class Solution {
    public long maxAlternatingSum(int[] arr) {
        int n=arr.length;
        long[] sq=new long[n];
        for(int i=0;i<n;i++) sq[i]=(long) arr[i]*arr[i];
        Arrays.sort(sq);

        long ans=0;
        int mid=n/2;
        for(int i=0;i<mid;i++) ans-=sq[i];
        for(int i=mid;i<n;i++) ans+=sq[i];
        return ans;
        
    }
}