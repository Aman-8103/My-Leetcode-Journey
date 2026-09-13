class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n=img1.length;
        List<Integer> one1=new ArrayList<>();
        List<Integer> one2=new ArrayList<>();

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(img1[i][j] == 1) one1.add(i*100+j);
                if(img2[i][j] == 1) one2.add(i*100+j);
            }
        }

        HashMap<Integer,Integer> map=new HashMap<>();
        int maxoverlap=0;

        for(int p1:one1){
            for(int p2:one2){
                int diff=p1-p2;
                map.put(diff, map.getOrDefault(diff,0)+1);
            }
        }
        for(int count:map.values()){
            maxoverlap=Math.max(maxoverlap,count);
        }

        return maxoverlap;
    }
}