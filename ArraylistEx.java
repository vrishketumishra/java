import java.util.ArrayList;
import java.util.Scanner;

public class ArraylistEx {
;
    public static void main(String[] args) {
        Scanner in = new Scanner((System.in));
        ArrayList<Integer> list=new ArrayList<>(10);
       /* list.add(10);
        list.add(27);
        list.add(980);
        list.add(11);
        list.add(87);
        list.add(10);
        list.add(27);
        list.add(980);
        list.add(11);
        list.add(87);
        list.add(10);
        list.add(27);
        list.add(980);
        list.add(11);
        list.add(87);
        System.out.println(list);
        System.out.println(list.contains(980));
        list.remove(1);
        list.set(0,92);
        System.out.println(list);*/
        System.out.println("enter the no.");
        for(int i=0;i<10;i++){
            list.add(in.nextInt());
        }
        System.out.println(list);
    }
}
