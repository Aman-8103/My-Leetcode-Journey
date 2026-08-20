class Solution {
    public int[] resultArray(int[] arr) {
        int n=arr.length;
        ArrayList<Integer> arr1=new ArrayList<>();
        ArrayList<Integer> arr2=new ArrayList<>();
        arr1.add(arr[0]);
        arr2.add(arr[1]);

        int index=2;
        int i=0,j=0;
        while(index < n){
            if(arr1.get(i) > arr2.get(j)){
                arr1.add(arr[index]);
                i++;
            }else{
                arr2.add(arr[index]);
                j++;
            }
            index++;
        }
        ArrayList<Integer> result=new ArrayList<>(arr1);
        result.addAll(arr2);

        int[] merge=new int[result.size()];
        for(i=0;i<result.size();i++){
            merge[i]=result.get(i);
        }
        return merge;
    }
}