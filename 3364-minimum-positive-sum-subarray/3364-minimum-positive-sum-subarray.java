class Solution {
    public int minimumSumSubarray(List<Integer> arr, int l, int r) {
        int n=arr.size();
        int minsum=Integer.MAX_VALUE;

        for(int k=l;k<=r;k++){
            int sum=0;
            for(int i=0;i<k;i++) sum+=arr.get(i);
            if(sum>0) minsum=Math.min(minsum,sum);

            for(int i=k;i<n;i++){
                sum+=arr.get(i)-arr.get(i-k);
                if(sum>0) minsum=Math.min(minsum,sum);
            }
        }
        return minsum==Integer.MAX_VALUE?-1:minsum;
    }
}