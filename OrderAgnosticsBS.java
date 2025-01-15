public class OrderAgnosticsBS{
public static void main(String[] args) {
        int[] arr= {18,12,-23,-34,-35,-56,-78,-88};
        int target= -56;
        int ans=orderAgnosticsBS(arr,target);
    System.out.println(ans);

    }
    static int orderAgnosticsBS(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        // find whether the array is ascending or descenfing
   boolean isAsc;
    if(arr[start]<arr[end])
    {
        isAsc = true;
    }
    else
        {
            isAsc = false;
        }
        while(start<=end){
            int mid= start +(end-start)/2;
            if(arr[mid]== target) {
                return mid;
            }
            if(target<arr[mid]){
                end = mid-1;
            }
            else if(target> arr[mid]){
                start= mid +1;
            }
            else{
                return mid;
            }
        }
        return -1;

    }
    }

