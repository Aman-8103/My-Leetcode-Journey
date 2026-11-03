class Solution {
    public int minMoves(int[] arr) {
        int n=arr.length;
        int highest=arr[0];
        int moves=0;
        for(int i:arr){
            if(i>highest) highest=i;
        }
        for(int i:arr) moves+=(highest-i);
        return moves;
    }
}