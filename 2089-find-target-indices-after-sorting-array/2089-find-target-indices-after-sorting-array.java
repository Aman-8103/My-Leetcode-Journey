class Solution {
    public List<Integer> targetIndices(int[] arr, int target) {
        int smallcount=0;
        int targetcount=0;
        for(int i:arr){
            if(i < target) smallcount++;
            else if(i == target) targetcount++;
        }
        
        List<Integer> ans=new ArrayList<>();
        for(int i=0;i<targetcount;i++){
            ans.add(smallcount+i);
        }

        return ans;
    }
}