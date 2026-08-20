class Solution {
    public int[] resultArray(int[] arr) {
        int n=arr.length;
        ArrayList<Integer> arr1=new ArrayList<>();
        ArrayList<Integer> arr2=new ArrayList<>();
        arr1.add(arr[0]);
        arr2.add(arr[1]);

        int index=2;
        while(index < n){
            if(arr1.get(arr1.size()-1) > arr2.get(arr2.size()-1)){
                arr1.add(arr[index]);
            }else{
                arr2.add(arr[index]);
            }
            index++;
        }
        arr1.addAll(arr2);

        for(int i=0;i<arr1.size();i++){
            arr[i]=arr1.get(i);
        }
        return arr;
    }
}