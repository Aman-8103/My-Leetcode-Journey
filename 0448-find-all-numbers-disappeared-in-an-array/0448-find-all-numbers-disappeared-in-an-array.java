class Solution {
    public List<Integer> findDisappearedNumbers(int[] arr) {
        int n=arr.length;
        int[] hash=new int[n+1];
        for(int i=0;i<n;i++){
            hash[arr[i]]++;
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(hash[i] == 0) ans.add(i);
        }
        return ans;

    }
}