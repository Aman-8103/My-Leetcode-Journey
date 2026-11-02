class Solution {
    public int minMoves(int[] arr) {
        int sum=0;
        int min=arr[0];

        for(int i:arr){
            if(i<min) min=i;
            sum+=i;
        }
        return sum-(arr.length*min);
    }
}