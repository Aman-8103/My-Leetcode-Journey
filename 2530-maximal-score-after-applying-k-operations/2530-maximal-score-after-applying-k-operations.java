class Solution {
    public long maxKelements(int[] arr, int k) {
        int n=arr.length;
        long score=0;

        PriorityQueue<Integer> maxheap = new PriorityQueue<>((a,b)->b-a);
        for(int i:arr) maxheap.add(i);
        for(int i=0;i<k;i++){
            int num=maxheap.poll();
            score+=(long)num;

            if(num%3==0){
                maxheap.add(num/3);
            }else{
                maxheap.add((num/3)+1);
            }
        }

        return score;
    }
}