class Solution {
    public int[] shuffle(int[] arr, int n) {
        int[] ans=new int[2*n];
        int[] temp1=new int[n];
        int[] temp2=new int[n];
        for(int i=0;i<n;i++) temp1[i]=arr[i];
        for(int i=n;i<2*n;i++) temp2[i%n]=arr[i];

        int i=0,j=0;
        int index=0;
        while(i<n && j<n){
            ans[index++]=temp1[i++];
            ans[index++]=temp2[j++];
        }
        return ans;
        
    }
}