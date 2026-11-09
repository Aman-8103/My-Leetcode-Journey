class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        Queue<Integer> free = new PriorityQueue<>();
        Queue<int[]> busy = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int[] worker = new int[k];
        int maxRequest = 0;

        for(int i=0; i<k; i++) {
            free.offer(i);
        }
        int K = 0;
        for(int i=0; i<arrival.length; i++) {
            int startTime = arrival[i];
            int endTime = arrival[i] + load[i];
            while(!busy.isEmpty() && busy.peek()[1] <= startTime) {
                int[] server = busy.poll();
                int modifiedId = i + ((server[0] - i) % k + k) % k; 
                free.offer(modifiedId);
            }
            if(!free.isEmpty()) {
                int server = free.poll();
                worker[server % k] += 1;
                busy.offer(new int[] {server, endTime});
                maxRequest = Math.max(maxRequest, worker[server % k]);
            }
        }
        List<Integer> busiestServerList = new LinkedList<>();
        for(int i=0; i<worker.length; i++) {
            if(worker[i] == maxRequest) {
                busiestServerList.add(i);
            }
        }
        return busiestServerList;
    }
}