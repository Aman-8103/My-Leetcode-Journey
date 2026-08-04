class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int[] arr=new int[max+1];
        for(int i:nums){
            arr[i]++;
        }
        List<Integer> ans=new ArrayList<>();
        for(int i=min;i<max;i++){
            if(arr[i] == 0) ans.add(i); 
        }

        return ans;
    }
}