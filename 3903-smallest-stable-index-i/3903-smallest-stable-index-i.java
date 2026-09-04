class Solution {

    public int firstStableIndex(int[] arr, int k) {
        int n=arr.length;

        int[] premin=new int[n];
        premin[n-1]=arr[n-1];
        for(int i=n-2;i>=0;i--){ // this for computing min from i-(n-1)
            premin[i]=Math.min(arr[i],premin[i+1]);
        }

        int max=arr[0];
        for(int i=0;i<n;i++){ //normal compute max and subtract with premin[i];
            if(arr[i] > max) max=arr[i];
            int min=premin[i];

            if(max-min <= k) return i;
        }
        return -1;
    }
}