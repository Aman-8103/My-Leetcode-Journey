class Solution {
    public int sumCounts(List<Integer> arr) {
        int n=arr.size();
        int sum=0;
        HashSet<Integer> set=new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                set.add(arr.get(j));
                sum+=set.size()*set.size();
            }
            set.clear();
        }
        return sum;   
    }
}