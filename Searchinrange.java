public class Searchinrange {
    public static void main(String[] args) {
        int[] arr={11,23,34,45,56,67,78,98,77,55,43};
        int target=77;
        int start=0;
        int end=4;
        int ans=linearSearch(arr,target,start,end);
        System.out.println(ans);
    }
    static int linearSearch(int[] arr, int target,int start,int end){
        if(arr.length==0){
            return -1;
        }
        for(int index=start;index<end;index++){
            int element=arr[index];
            if(element==target){
                return index;
            }
        }
        return-1;//if not found
    }
}

