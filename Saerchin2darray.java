import java.sql.SQLOutput;

public class Saerchin2darray {
    public static void main(String[] args) {
        int[][] arr={
            {1,2,3,},
            {4,5,6},
            {7,8,9,10},
        };
        int target=8;
        int[] ans= search(arr,target);
        System.out.println(Arrays.tostring(ans));
    }
    static int search(int[][] arr,int target){
        for(int row=0;row<arr.length;row++){
            for(int col=0;col<arr[row].length;col++){
                if(arr[row][col]==target){
                    return new int[]{row,col};
                }
            }
            //for max value initalise the max value with Integer.MIN_VALUE;
            //like max=Integer.MIN_VALUE;
        }
        return new int[]{-1,-1};
    }
}
