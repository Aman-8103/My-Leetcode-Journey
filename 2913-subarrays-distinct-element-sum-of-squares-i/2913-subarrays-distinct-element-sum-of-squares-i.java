class Solution {
    public int sumCounts(List<Integer> arr) {
        int n=arr.size();
        int sum=0;
        for(int i=0;i<n;i++){
            HashSet<Integer> set=new HashSet<>();
            for(int j=i;j<n;j++){
                set.add(arr.get(j));
                sum+=set.size()*set.size();
            }
        }
        return sum;   
    }
}