class Solution {
    public int[] getConcatenation(int[] arr) {
        int n=arr.length;
        int[] ans=new int[2*n];
        for(int i=0;i<n;i++){
            ans[i]=arr[i];
        }
        for(int i=n;i<2*n;i++){
            ans[i]=arr[i%n];
        }
        return ans;
        
    }
}