class Solution {
    public long maxKelements(int[] arr, int k) {
        int n=arr.length;
        long score=0;

        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Collections.reverseOrder());
        for(int i:arr) maxheap.add(i);
        for(int i=0;i<k;i++){
            int num=maxheap.poll();
            score+=num;
            maxheap.add((num+2)/3);
        }

        return score;
    }
}