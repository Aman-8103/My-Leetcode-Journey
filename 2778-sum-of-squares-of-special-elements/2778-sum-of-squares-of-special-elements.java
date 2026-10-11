class Solution {
    public int sumOfSquares(int[] arr) {
        int n=arr.length;
        int sum=0;
        for(int i=1;i<=n;i++){
            if(n%i == 0) sum+=arr[i-1]*arr[i-1];
        }
        return sum;
        
    }
}