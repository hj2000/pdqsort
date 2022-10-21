import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import SORT.Pdqsort;
public class test {
    public static void main(String[] args) {
        var int_ran=new Random();
        int_ran.setSeed(new Date().getTime());
        Integer[] Array=new Integer[6];
        //Arrays.asList(Array);
        for(int i=0;i<Array.length;i++){
            Array[i]=Integer.valueOf(int_ran.nextInt(100000));
        }        
        ArrayList<Integer> list=new ArrayList<>(Arrays.asList(Array));
        Collections.shuffle(list);
        var pdq=new Pdqsort<Integer>();
        Array =list.toArray(Array);
        pdq.insertion_sort(Array, 0, Array.length);
        Array =list.toArray(Array);
        pdq.heap_sort(Array, 0, Array.length);
        Array =list.toArray(Array);
        pdq.pdqsort(Array,0,Array.length);
        
    }
    
}
