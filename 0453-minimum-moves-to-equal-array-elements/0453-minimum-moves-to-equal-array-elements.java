class Solution {
    public int minMoves(int[] arr) {
        int sum=0;
        int min=arr[0];

        for(int i:arr){
            sum+=i;
            if(i<min) min=i;
        }
        return (sum-(arr.length*min));
    }
}