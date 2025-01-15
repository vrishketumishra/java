import java.sql.SQLOutput;

public class LinearSearch  {
    public static void main(String[] args) {
int[] arr={11,23,34,45,56,67,78,98,77,55,43};
int target=77;
int ans=linearSearch(arr,target);
        System.out.println(ans);
    }
    static int linearSearch(int[] arr, int target){
        if(arr.length==0){
            return -1;
        }
        for(int index=0;index<arr.length;index++){
            int element=arr[index];
            if(element==target){
                return index;
            }
        }
        return-1;//if not found
    }
}
