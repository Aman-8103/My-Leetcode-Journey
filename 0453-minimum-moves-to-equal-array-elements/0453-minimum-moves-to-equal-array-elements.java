class Solution {
    public int minMoves(int[] arr) {
        if(arr.length==0) return 0;
        int sum=0;
        int min=arr[0];

        for(int i:arr){
            sum+=i;
            if(i<min) min=i;
        }
        return (sum-(arr.length*min));
    }
}