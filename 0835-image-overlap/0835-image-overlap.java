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

       int[] counts = new int[3600];
        int maxoverlap = 0;
        
        // Step 3: Count shift frequencies using fast primitive array operations
        for (int p1 : one1) {
            int r1 = p1 / 100;
            int c1 = p1 % 100;
            
            for (int p2 : one2) {
                int r2 = p2 / 100;
                int c2 = p2 % 100;
                
                // Map 2D shift safely to a positive unique array index
                int index = (r1 - r2 + 30) * 60 + (c1 - c2 + 30);
                
                counts[index]++;
                if (counts[index] > maxoverlap) {
                    maxoverlap = counts[index];
                }
            }
        }

        return maxoverlap;
    }
}