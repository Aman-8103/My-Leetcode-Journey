class Solution {
    public int[] findErrorNums(int[] arr) {
        int n=arr.length;
        int[] hash=new int[n+1];
        int missing=-1;
        int repeat=-1;
        for(int i=0;i<n;i++) hash[arr[i]]++;
        for(int i=1;i<=n;i++){
            if(hash[i]==0) missing=i;
            if(hash[i] == 2) repeat=i;
        }
        return new int[]{repeat,missing};
        
    }
}