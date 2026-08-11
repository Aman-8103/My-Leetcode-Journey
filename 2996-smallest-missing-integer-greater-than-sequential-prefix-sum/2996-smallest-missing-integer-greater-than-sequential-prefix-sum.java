class Solution {
    public int missingInteger(int[] arr) {
        int n=arr.length;
        int prefixsum=arr[0];

        for(int i=1;i<n;i++){
            if(arr[i] == arr[i-1]+1){
                prefixsum+=arr[i];
            }else{
                break;
            }
            
        }

        HashSet<Integer> set=new HashSet<>();
        for(int i:arr){
            set.add(i);
        }

        while(set.contains(prefixsum)){
            prefixsum++;
        }
        return prefixsum;
    }
}